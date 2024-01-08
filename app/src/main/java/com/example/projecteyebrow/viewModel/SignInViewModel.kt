package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.CreateAccountUseCase
import com.example.domain.usecase.fireDB.profile.SaveUserProfileUseCase
import com.example.projecteyebrow.module.AppDispatcher
import com.example.projecteyebrow.module.AppDispatcherValue
import com.example.projecteyebrow.view.util.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val createUserAccountUseCase: CreateAccountUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    @AppDispatcher(AppDispatcherValue.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _signInState = MutableStateFlow<States>(States.Idle)
    val signInState: StateFlow<States> = _signInState.asStateFlow()

    private var _dbTransactionResult: Boolean = false

    fun createUserAccount(
        userEmail: String,
        userPassword: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        createUserAccountUseCase(userEmail, userPassword).collect { result ->
            _signInState.value = States.IsLoading

            if (result.isSuccess) {
                saveUserInformation(userEmail, userNickName)

                if (_dbTransactionResult) {
                    _signInState.value = States.IsFailed(Result.success(Unit))
                } else {
                    _signInState.value = States.IsFailed(Result.failure(Exception()))
                }
            } else {
                _signInState.value = States.IsFailed(Result.failure(Exception()))
            }
        }
        _signInState.value = States.Idle
    }

    private fun saveUserInformation(
        userEmail: String,
        userNickName: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        saveUserProfileUseCase(userEmail, userNickName).collect { result ->
            _dbTransactionResult = result.isSuccess
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}