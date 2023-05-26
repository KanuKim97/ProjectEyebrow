package com.example.projecteyebrow.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import com.example.projecteyebrow.view.viewItems.ProfileItem
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
    private val _isLogOutSuccess = MutableLiveData<Boolean>()
    val isLogOutSuccess: LiveData<Boolean> get() = _isLogOutSuccess

    val userProfile: Flow<ProfileItem> get() = fireDB.userProfile

    init { viewModelScope.launch(ioDispatcher) { fireDB.loadUserProfile() } }

    fun userAccountLogOut(): Job = viewModelScope.launch(ioDispatcher) {
        fireAuth.logOutUserAccount().collect { result ->
            if (result) {
                _isLogOutSuccess.postValue(true)
            } else {
                _isLogOutSuccess.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fireDB.stopEventListening()
        viewModelScope.cancel()
    }
}