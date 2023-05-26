package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireAuthProducer
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
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

    init { viewModelScope.launch(ioDispatcher) { fireAuth.getUserCurrentSession() } }

    //TODO("Implement Read Community Docs")

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}