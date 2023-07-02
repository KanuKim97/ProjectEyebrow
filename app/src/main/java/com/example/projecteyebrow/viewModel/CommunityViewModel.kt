package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CommunityItem
import com.example.domain.usecase.auth.GetCurrentUserSessionUseCase
import com.example.domain.usecase.fireDB.ReadAllCommunityContentUseCase
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getCurrentUserSessionUseCase: GetCurrentUserSessionUseCase,
    private val readAllCommunityContentUseCase: ReadAllCommunityContentUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val userCurrentSession: Flow<Boolean>
        get() = getCurrentUserSessionUseCase.currentSession
    val communityList: Flow<ArrayList<CommunityItem>>
        get() = readAllCommunityContentUseCase.communityItem

    init {
        viewModelScope.launch(ioDispatcher) {
            launch { getCurrentUserSessionUseCase() }.join()
            launch { readAllCommunityContentUseCase() }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}