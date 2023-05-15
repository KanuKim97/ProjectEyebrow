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
class CreateAccountViewModel @Inject constructor(
    private val fireAuth: AuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isCreateSuccess = MutableLiveData<Result<Unit>>()
    val isCreateSuccess: LiveData<Result<Unit>> get() = _isCreateSuccess

    fun createUserAccount(userEmail: String, userPassword: String): Job = viewModelScope
        .launch(ioDispatcher) {
            fireAuth.createUserAccount(userEmail, userPassword).collect { result ->
                _isCreateSuccess.postValue(result)
            }
        }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}