package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserProfileModel
import com.example.domain.usecase.auth.LogOutUseCase
import com.example.domain.usecase.fireDB.profile.LoadUserProfileUseCase
import com.example.projecteyebrow.module.IoDispatcher
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
class ProfileViewModel @Inject constructor(
    private val logOutUserAccountUseCase: LogOutUseCase,
    private val loadUserProfileUseCase: LoadUserProfileUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isLogOutState = MutableStateFlow<States>(States.Idle)
    val isLogOutState: StateFlow<States> = _isLogOutState.asStateFlow()

    private val _userProfile = MutableStateFlow(UserProfileModel("", ""))
    val userProfile: StateFlow<UserProfileModel> = _userProfile.asStateFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            loadUserProfileUseCase().collect { _userProfile.value = it }
        }
    }

    fun userAccountLogOut(): Job = viewModelScope.launch(ioDispatcher) {
        _isLogOutState.value = States.IsLoading

        logOutUserAccountUseCase().collect { result ->
            if (result) {
                _isLogOutState.value = States.IsSuccess(Result.success(Unit))
            } else {
                _isLogOutState.value = States.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _isLogOutState.value = States.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}