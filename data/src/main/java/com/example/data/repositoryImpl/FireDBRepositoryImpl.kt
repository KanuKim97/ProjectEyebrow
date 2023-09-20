package com.example.data.repositoryImpl

import com.example.data.entity.UserDataEntity
import com.example.domain.model.CommunityItem
import com.example.domain.model.ProfileItem
import com.example.domain.repository.FireDBRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class FireDBRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val userDB: FirebaseDatabase,
    private val communityDBRef: DatabaseReference
): FireDBRepository {
    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    private val _userRef: DatabaseReference by lazy { userDB.reference.child(_userUID) }
    private var fireDBEventListener: ValueEventListener? = null

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

    override fun loadUserProfile(): Flow<ProfileItem> = callbackFlow {
        fireDBEventListener = _userRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {  }

            override fun onCancelled(error: DatabaseError) {  }
        })
    }

    override fun readAllCommunityContent(): Flow<ArrayList<CommunityItem>> = flow<ArrayList<CommunityItem>> {
        lateinit var communityList: ArrayList<CommunityItem>

        fireDBEventListener = communityDBRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    communityList.add(it.getValue(CommunityItem::class.java) as CommunityItem)
                }
            }

            override fun onCancelled(error: DatabaseError) { throw error.toException() }
        })
    }.catch { exception ->
        when (exception) {
            is IOException -> throw IOException()
            else -> throw Exception()
        }
    }

    override fun uploadCommunityContent(
        uploadTitle: String,
        uploadContent: String
    ): Flow<Result<Unit>> = flow {
        val request = communityDBRef.push().setValue(CommunityItem(uploadTitle, uploadContent, _userUID))

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

    override fun stopEventListener() {
        fireDBEventListener?.apply {
            _userRef.removeEventListener(this)
            communityDBRef.removeEventListener(this)
        }
    }
}