package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projecteyebrow.di.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val appRepo: AppRepository): ViewModel() {
    private val _signInResult = MutableLiveData<Boolean>()
    val signInResult: LiveData<Boolean>
        get() = _signInResult

    fun signInAccount(userEmail: String, userPassword: String) =
        appRepo.createUserAccount(userEmail, userPassword)
            .addOnCompleteListener {
                if(it.isSuccessful) { _signInResult.postValue(true) }
            }
            .addOnFailureListener { _signInResult.postValue(false) }
}