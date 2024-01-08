package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.SendPWDResetEmailUseCase
import com.example.projecteyebrow.module.AppDispatcher
import com.example.projecteyebrow.module.AppDispatcherValue
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
class FindPWDViewModel @Inject constructor(
    private val sendPasswordResetEmailUseCase: SendPWDResetEmailUseCase,
    @AppDispatcher(AppDispatcherValue.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _resetEmailState = MutableStateFlow<States>(States.Idle)
    val resetEmailState: StateFlow<States> = _resetEmailState.asStateFlow()

    fun sendResetPassword(userEmail: String): Job = viewModelScope.launch(ioDispatcher) {
        sendPasswordResetEmailUseCase(userEmail).collect {
            _resetEmailState.value = States.IsLoading

            if (it.isSuccess) {
                _resetEmailState.value = States.IsSuccess(Result.success(Unit))
            } else {
                _resetEmailState.value = States.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _resetEmailState.value = States.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}