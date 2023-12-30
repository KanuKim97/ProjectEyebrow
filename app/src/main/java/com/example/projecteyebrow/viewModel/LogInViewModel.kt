package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogInUseCase
import com.example.projecteyebrow.module.IoDispatcher
import com.example.projecteyebrow.view.util.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val signInUserAccountUseCase: LogInUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _logInState = MutableStateFlow<States>(States.Idle)
    val logInState: StateFlow<States> = _logInState.asStateFlow()

    fun logInUserAccount(
        userEmail: String,
        userPassword: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        _logInState.value = States.IsLoading

        signInUserAccountUseCase(userEmail, userPassword).collect {
            if (it.isSuccess) {
                _logInState.value = States.IsSuccess(Result.success(Unit))
            } else {
                _logInState.value = States.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _logInState.value = States.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}