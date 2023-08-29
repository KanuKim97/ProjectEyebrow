package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TemporaryCommunityItem
import com.example.domain.usecase.roomDB.DeleteTempCommunityItemUseCase
import com.example.domain.usecase.roomDB.ReadTempCommunityItemUseCase
import com.example.projecteyebrow.Qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemporaryContentViewModel @Inject constructor(
    private val readTempCommunityItemUseCase: ReadTempCommunityItemUseCase,
    private val deleteTempCommunityItemUseCase: DeleteTempCommunityItemUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tempContent = MutableLiveData<List<TemporaryCommunityItem>>()
    private val _isDeleteSuccess = MutableLiveData<Result<Unit>>()

    val tempContent: LiveData<List<TemporaryCommunityItem>> get() = _tempContent
    val isDeleteSuccess: LiveData<Result<Unit>> get() = _isDeleteSuccess

    fun loadAllTempContent(): Job = viewModelScope.launch(ioDispatcher) {
        readTempCommunityItemUseCase().collect { result -> _tempContent.postValue(result) }
    }

    fun deleteTempContent(
        content: TemporaryCommunityItem
    ): Job = viewModelScope.launch(ioDispatcher) {
        deleteTempCommunityItemUseCase(content).collect { _isDeleteSuccess.postValue(it) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}