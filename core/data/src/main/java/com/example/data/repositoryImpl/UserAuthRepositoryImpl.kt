package com.example.data.repositoryImpl

import com.example.data.repository.UserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.io.IOException
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth
): UserAuthRepository {

    override fun getUserAuthState(): Flow<Boolean> = callbackFlow {
        fireAuth.addAuthStateListener { auth -> trySend(auth.currentUser != null) }
        awaitClose()
    }

    override fun createAccount(
        userEmail: String,
        userPassword: String
    ): Flow<Result<Unit>> = flow {
        try {
            fireAuth.createUserWithEmailAndPassword(userEmail, userPassword).await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(exception = e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    override fun logIn(
        userEmail: String,
        userPassword: String
    ): Flow<Result<Unit>> = flow {
        try {
            fireAuth.signInWithEmailAndPassword(userEmail, userPassword)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(exception = e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    override fun logOut(): Flow<Boolean> = callbackFlow {
        try {
            fireAuth.signOut()
            send(true)
            awaitClose()
        } catch (e: Exception) {
            send(false)
            awaitClose()
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(false)
            is IllegalStateException -> emit(false)
            else -> throw exception
        }
    }

    override fun sendPWDResetEmail(userEmail: String): Flow<Result<Unit>> = flow<Result<Unit>> {
        try {
            fireAuth.sendPasswordResetEmail(userEmail)
        } catch (e: Exception) {
            emit(Result.failure(exception = e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    override fun deleteAccount(): Flow<Result<Unit>> = flow {
        try {
            fireAuth.currentUser?.delete()?.await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(exception = e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

}