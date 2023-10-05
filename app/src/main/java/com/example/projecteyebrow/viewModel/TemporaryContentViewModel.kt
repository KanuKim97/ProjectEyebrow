package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TempContentModel
import com.example.domain.usecase.roomDB.DeleteTempContentUseCase
import com.example.domain.usecase.roomDB.ReadTempCotentUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemporaryContentViewModel @Inject constructor(
    private val readTempCommunityItemUseCase: ReadTempCotentUseCase,
    private val deleteTempCommunityItemUseCase: DeleteTempContentUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tempContent = MutableLiveData<List<TempContentModel>>()
    private val _isDeleteSuccess = MutableLiveData<Result<Unit>>()

    val tempContent: LiveData<List<TempContentModel>> get() = _tempContent
    val isDeleteSuccess: LiveData<Result<Unit>> get() = _isDeleteSuccess

    fun loadAllTempContent(): Job = viewModelScope.launch(ioDispatcher) {
        readTempCommunityItemUseCase().collect { result -> _tempContent.postValue(result) }
    }

    fun deleteTempContent(
        content: TempContentModel
    ): Job = viewModelScope.launch(ioDispatcher) {
        deleteTempCommunityItemUseCase(content).collect { _isDeleteSuccess.postValue(it) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}