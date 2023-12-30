package com.example.data.repositoryImpl

import com.example.domain.model.UserProfileModel
import com.example.domain.repository.UserProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
): UserProfileRepository {
    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }

    override fun saveUserProfile(
        userEmail: String,
        userName: String
    ): Flow<Result<Unit>> = callbackFlow {
        val userData = UserDataEntity(userName, userEmail)

        fireStore.collection("UsersInfo").document(_userUID).set(userData)
            .addOnSuccessListener { trySend(Result.success(Unit)) }
            .addOnFailureListener { e -> trySend(Result.failure(e)) }

        awaitClose()
    }

    override fun loadUserProfile(): Flow<UserProfileModel> = callbackFlow {
        fireStore.collection("UsersInfo").document(_userUID)
            .get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    val userProfile = UserProfileModel(
                        userName = document.data!!["userName"].toString(),
                        userEmail = document.data!!["userEmail"].toString()
                    )
                    trySend(userProfile)
                } else {
                    trySend(UserProfileModel("", ""))
                }
            }
            .addOnFailureListener { _ -> trySend(UserProfileModel("", "")) }

        awaitClose()
    }
}