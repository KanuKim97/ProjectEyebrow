package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.FireDBProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fireDB: FireDBProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    //TODO("Implement Something")

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}