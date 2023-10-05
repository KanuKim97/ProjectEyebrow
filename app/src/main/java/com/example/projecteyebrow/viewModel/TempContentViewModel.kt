package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TempContentModel
import com.example.domain.usecase.roomDB.DeleteTempContentUseCase
import com.example.domain.usecase.roomDB.ReadTempContentUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
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
    private val deleteTempContentUseCase: DeleteTempContentUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tempContents = MutableStateFlow<List<TempContentModel>>(listOf())
    val tempContents: StateFlow<List<TempContentModel>> get() = _tempContents.asStateFlow()

    fun loadTempContents(): Job = viewModelScope.launch(ioDispatcher) {
        readTempContentUseCase().collect { _tempContents.value = it }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}