package com.example.projecteyebrow.di.flow.producer

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class FireAuthProducer @Inject constructor(
    private val authModule: FirebaseAuth
) {
    private val _currentSession = MutableStateFlow<Boolean?>(null)
    val currentSession: Flow<Boolean> get() = _currentSession.filterNotNull()

    fun getUserCurrentSession() = authModule.addAuthStateListener {
        _currentSession.value = (it.currentUser != null)
    }

    fun createUserAccount(Email: String, Password: String): Flow<Result<Unit>> = flow {
        try {
            authModule.createUserWithEmailAndPassword(Email, Password).await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    fun signInUserAccount(Email: String, Password: String): Flow<Result<Unit>> = flow {
        try {
            authModule.signInWithEmailAndPassword(Email, Password).await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    fun sendPasswordResetEmail(Email: String): Flow<Result<Unit>> = flow {
        try {
            authModule.sendPasswordResetEmail(Email).await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    fun deleteUserAccount(): Flow<Result<Unit>> = flow {
        try {
            authModule.currentUser?.delete()?.await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(Result.failure(exception))
        } else {
            throw exception
        }
    }

    fun logOutUserAccount(): Flow<Boolean> = callbackFlow {
        try {
            authModule.signOut()
            send(true)
            awaitClose()
        } catch (e: Exception){
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

}