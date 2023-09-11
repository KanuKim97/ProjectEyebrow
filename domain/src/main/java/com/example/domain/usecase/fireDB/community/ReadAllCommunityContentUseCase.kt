package com.example.domain.usecase.fireDB.community

import com.example.domain.entity.CommunityItem
import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllCommunityContentUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    operator fun invoke(): Flow<ArrayList<CommunityItem>> = fireDB.readAllCommunityContent()
}