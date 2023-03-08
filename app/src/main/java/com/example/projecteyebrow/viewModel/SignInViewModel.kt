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
class SignInViewModel @Inject constructor(
    private val authRepo: AuthRepository
): ViewModel() {
    private val _signInTaskResult = MutableLiveData<Task<AuthResult>>()
    val signInTaskResult: LiveData<Task<AuthResult>> get() = _signInTaskResult

    fun signInUserAccount(userEmail: String, userPassword: String) =
        authRepo.createUserAccount(userEmail, userPassword)
            .addOnCompleteListener { task ->
                val isNewUser: Boolean = task.result.additionalUserInfo!!.isNewUser
                when {
                    task.isSuccessful && isNewUser -> _signInTaskResult.value = task
                    !task.isSuccessful -> task.exception?.printStackTrace()
                    !isNewUser -> task.exception?.printStackTrace()
                }
            }
            .addOnFailureListener { exception -> exception.printStackTrace() }
}