package com.example.domain.usecase.fireDB.community

import com.example.domain.model.ContentModel
import com.example.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllContentUseCase @Inject constructor(
    private val communityRepo: CommunityRepository
) {
    operator fun invoke(): Flow<ArrayList<ContentModel>> = communityRepo.readAllContents()
}