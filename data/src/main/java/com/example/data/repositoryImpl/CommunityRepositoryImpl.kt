package com.example.data.repositoryImpl

import android.net.Uri
import com.example.domain.model.ContentModel
import com.example.domain.repository.CommunityRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val _communityDBRef: DatabaseReference
): CommunityRepository {
    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    override fun readAllContents(): Flow<ArrayList<ContentModel>> = flow {
        val request: Task<DataSnapshot> = _communityDBRef.get()
        val result = arrayListOf<ContentModel>()

        if (request.isSuccessful) {
            request.result.children.forEach {
                result.add(it.getValue(ContentModel::class.java) as ContentModel)
            }
            emit(result)
        } else {
            emit(arrayListOf())
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(arrayListOf())
            is DatabaseException -> emit(arrayListOf())
            else -> throw exception
        }
    }

    override fun uploadContent(
        title: String,
        content: String,
        imageUrlList: List<Uri>
    ): Flow<Result<Unit>> = flow {
        val contentModel = ContentModel(title, content, imageUrlList, _userUID)
        val request: Task<Void> = _communityDBRef.push().setValue(contentModel)

        if (request.isSuccessful) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception()))
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(IOException()))
            is DatabaseException -> emit(Result.failure(Exception()))
            else -> throw exception
        }
    }
}