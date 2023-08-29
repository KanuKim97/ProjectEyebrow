package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ProfileItem
import com.example.domain.usecase.auth.LogOutUserAccountUseCase
import com.example.domain.usecase.fireDB.LoadUserProfileUseCase
import com.example.domain.usecase.fireDB.StopEventListenUseCase
import com.example.projecteyebrow.Qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOutUserAccountUseCase: LogOutUserAccountUseCase,
    private val loadUserProfileUseCase: LoadUserProfileUseCase,
    private val stopEventListenUseCase: StopEventListenUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isLogOutSuccess = MutableLiveData<Boolean>()
    val isLogOutSuccess: LiveData<Boolean> get() = _isLogOutSuccess
    val userProfile: Flow<ProfileItem> get() = loadUserProfileUseCase.userProfile

    init { viewModelScope.launch(ioDispatcher) { loadUserProfileUseCase() } }

    fun userAccountLogOut(): Job = viewModelScope.launch(ioDispatcher) {
        logOutUserAccountUseCase().collect { result ->
            if (result) {
                _isLogOutSuccess.postValue(true)
            } else {
                _isLogOutSuccess.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopEventListenUseCase()
        viewModelScope.cancel()
    }
}