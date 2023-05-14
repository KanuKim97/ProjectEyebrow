package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.AuthProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val fireAuth: AuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isLogInSuccess = MutableLiveData<Result<Unit>>()
    val isLogInSuccess: LiveData<Result<Unit>> get() = _isLogInSuccess

    fun logInUserAccount(userEmail: String, userPassword: String): Job =
        viewModelScope.launch(ioDispatcher) {
            fireAuth.signInUserAccount(userEmail, userPassword).collect { result ->
                _isLogInSuccess.postValue(result)
            }
        }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}