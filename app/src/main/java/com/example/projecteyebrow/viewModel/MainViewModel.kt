package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fireAuth: FireAuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isUserSessionAlive = MutableLiveData<FirebaseUser?>()
    val isUserSessionAlive: LiveData<FirebaseUser?> get() = _isUserSessionAlive

    init { provideCurrentSession() }

    private fun provideCurrentSession(): Job = viewModelScope.launch(ioDispatcher) {
        fireAuth.getCurrentUser().collect { userSession ->
            _isUserSessionAlive.postValue(userSession)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}