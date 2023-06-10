package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.SendPasswordResetEmailUseCase
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val sendPasswordResetEmailUseCase: SendPasswordResetEmailUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isResetEmailSend = MutableLiveData<Result<Unit>>()
    val isResetEmailSend: LiveData<Result<Unit>> get() = _isResetEmailSend

    fun sendResetPassword(userEmail: String): Job = viewModelScope.launch(ioDispatcher) {
        sendPasswordResetEmailUseCase(userEmail).collect { result ->
            _isResetEmailSend.postValue(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}