package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogInUserAccountUseCase
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val signInUserAccountUseCase: LogInUserAccountUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isLogInSuccess = MutableLiveData<Result<Unit>>()
    val isLogInSuccess: LiveData<Result<Unit>> get() = _isLogInSuccess

    fun logInUserAccount(
        userEmail: String,
        userPassword: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        signInUserAccountUseCase(userEmail, userPassword).collect {
            _isLogInSuccess.postValue(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}