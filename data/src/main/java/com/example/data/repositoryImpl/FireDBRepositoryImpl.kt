package com.example.data.repositoryImpl

import android.net.Uri
import com.example.data.entity.UserDataEntity
import com.example.domain.model.ContentModel
import com.example.domain.model.UserProfileModel
import com.example.domain.repository.FireDBRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FireDBRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val userDB: FirebaseDatabase,
    private val _communityDBRef: DatabaseReference
): FireDBRepository {
    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    private val _userRef: DatabaseReference by lazy { userDB.reference.child(_userUID) }

    override fun saveUserProfile(
        userEmail: String,
        userName: String
    ): Flow<Result<Unit>> = flow {
        val userData = UserDataEntity(userName, userEmail)
        val saveUserTask: Task<Void> = _userRef.setValue(userData)

        if (saveUserTask.isSuccessful) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception()))
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(IOException()))
            else -> emit(Result.failure(Exception()))
        }
    }

    override fun loadUserProfile(): Flow<UserProfileModel> = flow {
        val readRequest: Task<DataSnapshot> = _userRef.get()

        if (readRequest.isSuccessful) {
            readRequest.result.value
        }
    }

    override fun readAllContent(): Flow<ArrayList<ContentModel>> = flow {
        val readRequest: Task<DataSnapshot> = _communityDBRef.get()
        val readResult = arrayListOf<ContentModel>()

        if (readRequest.isSuccessful) {
            readRequest.result.children.forEach {
                readResult.add(it.getValue(ContentModel::class.java) as ContentModel)
            }
            emit(readResult)
        } else {
            emit(arrayListOf())
        }
    }.catch { exception ->
        when(exception) {
            is IOException -> emit(arrayListOf())
            else -> emit(arrayListOf())
        }
    }

    override fun uploadContent(
        title: String,
        content: String,
        imageUriList: List<Uri>
    ): Flow<Result<Unit>> = flow {
        val contentModel = ContentModel(title, content, imageUriList, _userUID)
        val request: Task<Void> = _communityDBRef.push().setValue(contentModel)

        if (request.isSuccessful) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception()))
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(IOException()))
            else -> throw exception
        }
    }
}