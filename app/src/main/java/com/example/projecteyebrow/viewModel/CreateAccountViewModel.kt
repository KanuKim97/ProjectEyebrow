package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val fireAuth: FireAuthProducer,
    private val fireDB: FireDBProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isCreateSuccess = MutableLiveData<Result<Unit>>()
    val isCreateSuccess: LiveData<Result<Unit>> get() = _isCreateSuccess

    fun createUserAccount(
        userEmail: String,
        userPassword: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        fireAuth.createUserAccount(userEmail, userPassword).collect { result ->
            if (result.isSuccess) {
                saveUserInformation(userEmail, userNickName)
                _isCreateSuccess.postValue(result)
            } else {
                _isCreateSuccess.postValue(result)
            }
        }
    }

    private fun saveUserInformation(
        userEmail: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        fireDB.saveUserProfile(userEmail, userNickName)

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}