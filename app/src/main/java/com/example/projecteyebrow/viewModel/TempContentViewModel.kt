package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.roomDB.ReadTempContentUseCase
import com.example.model.TempContent
import com.example.projecteyebrow.module.AppDispatcher
import com.example.projecteyebrow.module.AppDispatcherValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TempContentViewModel @Inject constructor(
    private val readTempContentUseCase: ReadTempContentUseCase,
    @AppDispatcher(AppDispatcherValue.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tempContents = MutableStateFlow<List<TempContent>>(listOf())
    val tempContents: StateFlow<List<TempContent>> get() = _tempContents.asStateFlow()

    init { loadTempContents() }

    private fun loadTempContents(): Job = viewModelScope.launch(ioDispatcher) {
        readTempContentUseCase().collect { _tempContents.value = it }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}