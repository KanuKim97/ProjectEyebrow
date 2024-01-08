package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.GetUserAuthStateUseCase
import com.example.domain.usecase.fireDB.community.ReadAllContentUseCase
import com.example.model.ContentModel
import com.example.projecteyebrow.module.AppDispatcher
import com.example.projecteyebrow.module.AppDispatcherValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getCurrentUserSessionUseCase: GetUserAuthStateUseCase,
    private val readAllCommunityContentUseCase: ReadAllContentUseCase,
    @AppDispatcher(AppDispatcherValue.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _communityList = MutableStateFlow<ArrayList<ContentModel>>(arrayListOf())
    val communityList: StateFlow<ArrayList<ContentModel>> = _communityList.asStateFlow()

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