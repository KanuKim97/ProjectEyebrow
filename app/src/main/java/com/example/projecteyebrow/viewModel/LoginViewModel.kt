package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projecteyebrow.di.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepository
): ViewModel() {
    private val _logInTaskResult = MutableLiveData<Task<AuthResult>>()
    val logInTaskResult: LiveData<Task<AuthResult>> get() = _logInTaskResult

    fun logInUserAccount(userEmail: String, userPassword: String) =
        authRepo.signInUserAccount(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _logInTaskResult.value = task
                } else {
                    task.exception?.printStackTrace()
                }
            }
            .addOnFailureListener { exception -> exception.printStackTrace() }
}