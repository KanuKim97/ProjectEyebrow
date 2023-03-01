package com.example.projecteyebrow.di.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/* FireBase Auth Repository */
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun getCurrentUserSession(): Boolean = firebaseAuth.currentUser != null

    fun createUserAccount(userEmail: String, userPassword: String) =
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)

    fun signInUserAccount(userEmail: String, userPassword: String) =
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)

    fun resetUserAccountPassword(userEmail: String) =
        firebaseAuth.sendPasswordResetEmail(userEmail)

    fun deleteUserAccount() = firebaseAuth.currentUser?.delete()

    fun signOutUserAccount() = firebaseAuth.signOut()
}