package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {
    fun getUserAuthState(): Flow<Boolean>

    fun createAccount(userEmail: String, userPassword: String): Flow<Result<Unit>>

    fun deleteAccount(): Flow<Result<Unit>>

    fun logIn(userEmail: String, userPassword: String): Flow<Result<Unit>>

    fun logOut(): Flow<Boolean>

    fun sendPWDResetEmail(userEmail: String): Flow<Result<Unit>>
}