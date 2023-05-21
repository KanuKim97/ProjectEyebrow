package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val fireAuth: FireAuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val isLogOutSuccess: Flow<Result<Unit>> get() = fireAuth.isSignOutSuccess


    fun logOutUserAccount(): Job = viewModelScope.launch(ioDispatcher) {
        fireAuth.signOutUserAccount()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}