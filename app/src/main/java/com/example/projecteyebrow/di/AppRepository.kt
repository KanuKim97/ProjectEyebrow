package com.example.projecteyebrow.di

import com.example.projecteyebrow.data.module.FirebaseClient
import com.example.projecteyebrow.database.dao.UserDao
import com.example.projecteyebrow.database.tables.UserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AppRepository @Inject constructor ( private val userDao: UserDao ) {
    /* -- Room DAO function -- */
    val allUserData = userDao.readAllShopData()
    suspend fun addUserData(userEntity: UserEntity) = userDao.addShopData(userEntity)
    suspend fun deleteUserdata(userEntity: UserEntity) = userDao.deleteShopData(userEntity)

    /* -- FireBase Auth -- */
    private val fireAuthService: FirebaseAuth = FirebaseClient.getAuthClient()

    fun getFireUserSession(): Boolean = fireAuthService.currentUser != null
    fun logInUserAccount(userEmail: String, userPassword: String): Task<AuthResult> =
        fireAuthService.signInWithEmailAndPassword(userEmail, userPassword)
    fun createUserAccount(userEmail: String, userPassword: String) =
        fireAuthService.createUserWithEmailAndPassword(userEmail, userPassword)
    fun deleteUserAccount() = fireAuthService.currentUser?.delete()
    fun findUserPassword(userEmail: String) = fireAuthService.sendPasswordResetEmail(userEmail)
    fun userSignOut() = fireAuthService.signOut()
}