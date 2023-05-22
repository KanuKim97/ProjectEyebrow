package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fireDB: FireDBProducer,
    private val fireAuth: FireAuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val profileInformation: Flow<String> get() = fireDB.userProfile
    val isLogOutSuccess: Flow<Result<Unit>> get() = fireAuth.isSignOutSuccess

    init { loadUserProfile() }

    fun logOutUserAccount(): Job = viewModelScope.launch(ioDispatcher) {
        fireAuth.signOutUserAccount()
    }

    private fun loadUserProfile(): Job = viewModelScope.launch(ioDispatcher) {
        fireDB.loadUserProfile()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}