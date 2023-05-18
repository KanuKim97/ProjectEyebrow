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
class WriteContentViewModel @Inject constructor(
    private val roomDB: RoomProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSaveSuccess = MutableLiveData<Result<Unit>>()
    val isSaveSuccess: LiveData<Result<Unit>> get() = _isSaveSuccess


    fun temporarySaveContent(
        contentTitle: String,
        temporaryContent: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        roomDB.saveTemporaryContent(
            TemporaryContentEntity(
                contentID = 0,
                contentTitle = contentTitle,
                TemporaryContent = temporaryContent,
                contentTime = System.currentTimeMillis().toString()
            )
        ).collect { result ->
            _isSaveSuccess.postValue(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}