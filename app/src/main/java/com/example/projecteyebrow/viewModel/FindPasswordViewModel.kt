package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projecteyebrow.di.AppRepository

class FindPasswordViewModel(private val appRepo: AppRepository): ViewModel() {
    private val _emailResetValue = MutableLiveData<Boolean>()
    val emailResetValue: LiveData<Boolean>
        get() = _emailResetValue

    fun sendPasswordResetEmail(userEmail: String) =
        appRepo.findUserPassword(userEmail)
            .addOnCompleteListener {
                if (it.isSuccessful) { _emailResetValue.value = true }
            }
            .addOnFailureListener { _emailResetValue.value = false }
}