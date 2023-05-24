package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecteyebrow.database.tables.TemporaryContentEntity
import com.example.projecteyebrow.di.dispatcherQualifier.IoDispatcher
import com.example.projecteyebrow.di.flow.producer.RoomProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemporaryContentViewModel @Inject constructor(
    private val tempContentDB: RoomProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tempContent = MutableLiveData<List<TemporaryContentEntity>>()
    val tempContent: LiveData<List<TemporaryContentEntity>> get() = _tempContent

    private val _isDeleteSuccess = MutableLiveData<Result<Unit>>()
    val isDeleteSuccess: LiveData<Result<Unit>> get() = _isDeleteSuccess

    fun loadAllTempContent(): Job = viewModelScope.launch(ioDispatcher) {
        tempContentDB.readAllTemporaryContent().collect { result ->
            _tempContent.postValue(result)
        }
    }

    fun deleteTempContent(content: TemporaryContentEntity): Job =
        viewModelScope.launch(ioDispatcher) {
            tempContentDB.deleteTemporaryContent(content).collect {
                _isDeleteSuccess.postValue(it)
            }
        }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}