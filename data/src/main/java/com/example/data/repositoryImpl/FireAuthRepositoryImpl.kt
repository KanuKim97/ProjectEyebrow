package com.example.data.repositoryImpl

import com.example.domain.repository.FireAuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.io.IOException
import javax.inject.Inject

class FireAuthRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth
): FireAuthRepository {
    private val _currentSession = MutableStateFlow<Boolean?>(null)
    override val currentSession: Flow<Boolean> get() = _currentSession.filterNotNull()

    override fun getUserCurrentSession() = fireAuth.addAuthStateListener { auth ->
        _currentSession.value = (auth.currentUser != null)
    }

    override fun createUserAccount(
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

    override fun logInUserAccount(
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

    override fun logOutUserAccount(): Flow<Boolean> = callbackFlow {
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

    override fun sendPasswordResetEmail(userEmail: String): Flow<Result<Unit>> = flow<Result<Unit>> {
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

    override fun deleteUserAccount(): Flow<Result<Unit>> = flow {
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