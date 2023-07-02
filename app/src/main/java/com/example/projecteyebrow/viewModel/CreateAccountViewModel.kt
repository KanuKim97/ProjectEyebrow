package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.CreateUserAccountUseCase
import com.example.domain.usecase.fireDB.SaveUserProfileUseCase
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val createUserAccountUseCase: CreateUserAccountUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isCreateSuccess = MutableLiveData<Result<Unit>>()
    val isCreateSuccess: LiveData<Result<Unit>> get() = _isCreateSuccess

    fun createUserAccount(
        userEmail: String,
        userPassword: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        createUserAccountUseCase(userEmail, userPassword).collect { result ->
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
    ): Job = viewModelScope.launch(ioDispatcher) { saveUserProfileUseCase(userEmail, userNickName) }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}