package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogInUserAccountUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
import com.example.projecteyebrow.view.util.state.LogInState
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
    private val signInUserAccountUseCase: LogInUserAccountUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _logInState = MutableStateFlow<LogInState>(LogInState.Idle)
    val logInState: StateFlow<LogInState> = _logInState.asStateFlow()

    fun logInUserAccount(
        userEmail: String,
        userPassword: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        _logInState.value = LogInState.IsLoading

        signInUserAccountUseCase(userEmail, userPassword).collect {
            if (it.isSuccess) {
                _logInState.value = LogInState.IsSuccess(Result.success(Unit))
            } else {
                _logInState.value = LogInState.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _logInState.value = LogInState.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}