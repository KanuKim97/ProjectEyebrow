package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import com.example.projecteyebrow.view.viewItems.CommunityItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val fireDB: FireDBProducer,
    private val fireAuth: FireAuthProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val userCurrentSession: Flow<Boolean> get() = fireAuth.currentSession
    val communityList: Flow<ArrayList<CommunityItems>> get() = fireDB.communityItems

    init {
        viewModelScope.launch(ioDispatcher) {
            launch { fireAuth.getUserCurrentSession() }.join()
            launch { fireDB.readCommunityContent() }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}