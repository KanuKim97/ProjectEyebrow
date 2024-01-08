package com.example.domain.usecase.fireDB.community

import android.net.Uri
import com.example.data.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadContentUseCase @Inject constructor(
    private val communityRepo: CommunityRepository
) {
    operator fun invoke(
        title: String,
        content: String,
        imageList: List<Uri>
    ): Flow<Result<Unit>> = communityRepo.uploadContent(title, content, imageList)
}