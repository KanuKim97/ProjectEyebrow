package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projecteyebrow.di.AppRepository

class LoginViewModel(private val appRepo: AppRepository): ViewModel() {
    private val _logInData = MutableLiveData<Boolean>()
    val logInData: LiveData<Boolean>
        get() = _logInData

    fun logInUser(userEmail: String, userPassword: String) =
        appRepo.logInUserAccount(userEmail, userPassword)
            .addOnCompleteListener {
                if (it.isSuccessful) { _logInData.value = true }
            }
            .addOnFailureListener { _logInData.value = false }
}