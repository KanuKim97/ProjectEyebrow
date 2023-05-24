package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val fireDB: FireDBProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}