package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projecteyebrow.di.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val authRepo: AuthRepository
): ViewModel() {
    private val _emailResetValue = MutableLiveData<Boolean>()
    val emailResetValue: LiveData<Boolean> get() = _emailResetValue

    fun sendPasswordResetEmail(userEmail: String) =
        authRepo.resetUserAccountPassword(userEmail)
            .addOnCompleteListener { task -> _emailResetValue.value = task.isSuccessful }
            .addOnFailureListener { exception -> exception.printStackTrace() }
}