package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.AuthProducer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authProducer: AuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _signInTaskResult = MutableLiveData<Result<Unit>>()
    val signInTaskResult: LiveData<Result<Unit>> get() = _signInTaskResult

    fun createUserAccount(userEmail: String, userPassword: String): Job =
        viewModelScope.launch(ioDispatcher) {
            authProducer.createUserAccount(userEmail, userPassword).collect { result ->
                if (result.isSuccess) {
                    _signInTaskResult.postValue(result)
                } else {
                    _signInTaskResult.postValue(result)
                }
            }
        }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}