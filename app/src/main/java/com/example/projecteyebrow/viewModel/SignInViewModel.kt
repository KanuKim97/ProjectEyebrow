package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.CreateAccountUseCase
import com.example.domain.usecase.fireDB.profile.SaveUserProfileUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
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
class SignInViewModel @Inject constructor(
    private val createUserAccountUseCase: CreateAccountUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _profileState = MutableStateFlow<States>(States.Idle)
    private val _signInState = MutableStateFlow<States>(States.Idle)

    val signInState: StateFlow<States> = _signInState.asStateFlow()

    fun createUserAccount(
        userEmail: String,
        userPassword: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        createUserAccountUseCase(userEmail, userPassword).collect { result ->
            _signInState.value = States.IsLoading
            saveUserInformation(userEmail, userNickName)

            if (result.isSuccess && (_profileState.value == States.IsSuccess(Result.success(Unit)))) {
                _signInState.value = States.IsSuccess(Result.success(Unit))
            } else {
                _signInState.value = States.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _signInState.value = States.Idle
            }
        }
    }

    private fun saveUserInformation(
        userEmail: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        saveUserProfileUseCase(userEmail, userNickName).collect { result ->
            _profileState.value = States.IsLoading

            if (result.isSuccess) {
                _profileState.value = States.IsSuccess(Result.success(Unit))
            } else {
                _profileState.value = States.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _profileState.value = States.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}