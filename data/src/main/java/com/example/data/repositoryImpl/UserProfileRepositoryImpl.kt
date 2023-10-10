package com.example.data.repositoryImpl

import com.example.data.entity.UserDataEntity
import com.example.domain.model.UserProfileModel
import com.example.domain.repository.UserProfileRepository
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

class UserProfileRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val userDB: FirebaseDatabase
): UserProfileRepository {
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
}