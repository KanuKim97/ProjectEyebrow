package com.example.projecteyebrow.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TemporaryCommunityItem
import com.example.domain.usecase.fireDB.community.UploadCommunityContentUseCase
import com.example.domain.usecase.roomDB.SaveTempCommunityItemUseCase
import com.example.projecteyebrow.qualifier.IoDispatcher
import com.example.projecteyebrow.view.util.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteContentViewModel @Inject constructor(
    private val uploadCommunityContentUseCase: UploadCommunityContentUseCase,
    private val saveTempCommunityItemUseCase: SaveTempCommunityItemUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSaveSuccess = MutableStateFlow<States>(States.Idle)
    val isSaveSuccess: StateFlow<States> get() = _isSaveSuccess.asStateFlow()


    fun temporarySaveContent(
        tempTitle: String,
        tempContent: String,
        tempImageList: List<Uri>
    ): Job = viewModelScope.launch(ioDispatcher) {
        saveTempCommunityItemUseCase(
            TemporaryCommunityItem(
                tempID = 0,
                tempTitle = tempTitle,
                tempContent = tempContent,
                tempImageUriList = tempImageList,
                timeStamp = System.currentTimeMillis().toString()
            )
        ).collect {
            _isSaveSuccess.value = States.IsLoading

            if (it.isSuccess) {
                _isSaveSuccess.value = States.IsSuccess(Result.success(Unit))
            } else {
                _isSaveSuccess.value = States.IsFailed(Result.failure(Exception()))
                delay(3000L)
                _isSaveSuccess.value = States.Idle
            }
        }
    }

    /* TODO("Fix this function") */
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