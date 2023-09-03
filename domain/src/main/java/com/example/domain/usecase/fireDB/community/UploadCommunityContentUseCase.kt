package com.example.domain.usecase.fireDB.community

import com.example.domain.repository.FireDBRepository
import javax.inject.Inject

class UploadCommunityContentUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    suspend operator fun invoke(
        uploadTitle: String,
        uploadContent: String
    ): Unit = fireDB.uploadCommunityContent(uploadTitle, uploadContent)
}