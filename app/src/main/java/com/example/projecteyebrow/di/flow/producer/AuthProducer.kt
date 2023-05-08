package com.example.projecteyebrow.di.flow.producer

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class AuthProducer @Inject constructor(
    private val authModule: FirebaseAuth
) {
    private val _isSignOutSuccess = MutableStateFlow<Result<Unit>?>(null)
    val isSignOutSuccess: Flow<Result<Unit>> get() = _isSignOutSuccess.filterNotNull()

    fun getCurrentUser(): Flow<FirebaseUser?> = flow {
        emit(authModule.currentUser)
    }.catch { exception ->
        if (exception is IOException) {
            emit(null)
        } else {
            throw exception
        }
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

    fun signOutUserAccount(): Unit {
        try {
            authModule.signOut()
            _isSignOutSuccess.value = Result.success(Unit)
        } catch (e: Exception) {
            _isSignOutSuccess.value = Result.failure(e)
        }
    }
}