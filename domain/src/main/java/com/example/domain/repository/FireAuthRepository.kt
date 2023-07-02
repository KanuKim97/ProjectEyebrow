package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface FireAuthRepository {
    val currentSession: Flow<Boolean>

    fun getUserCurrentSession()

    fun createUserAccount(userEmail: String, userPassword: String): Flow<Result<Unit>>

    fun logInUserAccount(userEmail: String, userPassword: String): Flow<Result<Unit>>

    fun logOutUserAccount(): Flow<Boolean>

    fun sendPasswordResetEmail(userEmail: String): Flow<Result<Unit>>

    fun deleteUserAccount(): Flow<Result<Unit>>
}