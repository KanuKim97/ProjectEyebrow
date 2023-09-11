package com.example.domain.usecase.fireDB.community

import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadCommunityContentUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    operator fun invoke(
        uploadTitle: String,
        uploadContent: String
    ): Flow<Result<Unit>> = fireDB.uploadCommunityContent(uploadTitle, uploadContent)
}