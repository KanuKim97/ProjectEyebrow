package com.example.projecteyebrow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TemporaryCommunityItem
import com.example.domain.usecase.fireDB.UploadCommunityContentUseCase
import com.example.domain.usecase.roomDB.SaveTempCommunityItemUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteContentViewModel @Inject constructor(
    private val uploadCommunityContentUseCase: UploadCommunityContentUseCase,
    private val saveTempCommunityItemUseCase: SaveTempCommunityItemUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSaveSuccess = MutableLiveData<Result<Unit>>()
    val isSaveSuccess: LiveData<Result<Unit>> get() = _isSaveSuccess

    fun temporarySaveContent(
        contentTitle: String,
        temporaryContent: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        saveTempCommunityItemUseCase(
            TemporaryCommunityItem(
                contentID = 0,
                title = contentTitle,
                content = temporaryContent,
                contentImage = null,
                contentTimeStamp = System.currentTimeMillis().toString()
            )
        ).collect { _isSaveSuccess.postValue(it) }
    }

    fun uploadCommunityContent(
        uploadTitle: String,
        uploadContent: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        uploadCommunityContentUseCase(uploadTitle, uploadContent)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}