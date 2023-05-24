package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import com.example.projecteyebrow.view.viewItems.ProfileItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fireDB: FireDBProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val userProfile: Flow<ProfileItem> get() = fireDB.userProfile

    init { viewModelScope.launch(ioDispatcher) { fireDB.loadUserProfile() } }

    override fun onCleared() {
        super.onCleared()
        fireDB.stopEventListen()
        viewModelScope.cancel()
    }
}