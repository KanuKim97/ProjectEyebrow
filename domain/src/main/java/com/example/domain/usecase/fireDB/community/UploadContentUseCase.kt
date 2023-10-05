package com.example.domain.usecase.fireDB.community

import android.net.Uri
import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadContentUseCase @Inject constructor(
    private val fireDBRepo: FireDBRepository
) {
    operator fun invoke(
        title: String,
        content: String,
        imageList: List<Uri>
    ): Flow<Result<Unit>> = fireDBRepo.uploadContent(title, content, imageList)
}