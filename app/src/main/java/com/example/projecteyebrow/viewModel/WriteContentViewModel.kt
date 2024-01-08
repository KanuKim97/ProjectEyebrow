package com.example.projecteyebrow.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.fireDB.community.UploadContentUseCase
import com.example.domain.usecase.roomDB.SaveTempContentUseCase
import com.example.model.TempContent
import com.example.projecteyebrow.module.AppDispatcher
import com.example.projecteyebrow.module.AppDispatcherValue
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
    private val uploadCommunityContentUseCase: UploadContentUseCase,
    private val saveTempCommunityItemUseCase: SaveTempContentUseCase,
    @AppDispatcher(AppDispatcherValue.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSaveSuccess = MutableStateFlow<States>(States.Idle)
    private val _isUploadSuccess = MutableStateFlow<States>(States.Idle)

    val isSaveSuccess: StateFlow<States> get() = _isSaveSuccess.asStateFlow()
    val isUploadSuccess: StateFlow<States> get() = _isSaveSuccess.asStateFlow()

    fun temporarySaveContent(
        tempTitle: String,
        tempContent: String,
        tempImageList: List<Uri>
    ): Job = viewModelScope.launch(ioDispatcher) {
        saveTempCommunityItemUseCase(
            TempContent(
                tempID = 0,
                tempTitle = tempTitle,
                tempContent = tempContent,
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

    fun uploadCommunityContent(
        title: String,
        content: String,
        imageUriList: List<Uri>
    ): Job = viewModelScope.launch(ioDispatcher) {
        uploadCommunityContentUseCase(
            title = title,
            content = content,
            imageList = imageUriList
        ).collect {
            _isUploadSuccess.value = States.IsLoading

            if (it.isSuccess) {
                _isUploadSuccess.value = States.IsSuccess(Result.success(Unit))
            } else {
                _isUploadSuccess.value = States.IsFailed(Result.failure(Exception()))
                delay(3000L)
                _isUploadSuccess.value = States.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}