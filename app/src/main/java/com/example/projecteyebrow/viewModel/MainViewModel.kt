package com.example.projecteyebrow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.GetUserAuthStateUseCase
import com.example.projecteyebrow.module.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserAuthStateUseCase: GetUserAuthStateUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isAuthAlive = MutableStateFlow<Boolean?>(null)
    val isAuthAlive: Flow<Boolean> get() = _isAuthAlive.filterNotNull()

    init {
        viewModelScope.launch(ioDispatcher) {
            getUserAuthStateUseCase().collect { _isAuthAlive.value = it }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}