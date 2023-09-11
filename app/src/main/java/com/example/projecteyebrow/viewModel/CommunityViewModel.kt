package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CommunityItem
import com.example.domain.usecase.auth.GetCurrentUserSessionUseCase
import com.example.domain.usecase.fireDB.community.ReadAllCommunityContentUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getCurrentUserSessionUseCase: GetCurrentUserSessionUseCase,
    private val readAllCommunityContentUseCase: ReadAllCommunityContentUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _communityList = MutableStateFlow<ArrayList<CommunityItem>>(arrayListOf())
    val communityList: StateFlow<ArrayList<CommunityItem>> = _communityList.asStateFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            launch {
                getCurrentUserSessionUseCase()
            }.join()

            launch { readAllCommunityContentUseCase().collect { _communityList.value = it } }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}