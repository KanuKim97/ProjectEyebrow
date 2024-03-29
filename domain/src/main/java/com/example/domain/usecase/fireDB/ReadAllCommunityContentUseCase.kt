package com.example.domain.usecase.fireDB

import com.example.domain.entity.CommunityItem
import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllCommunityContentUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    val communityItem: Flow<ArrayList<CommunityItem>> = fireDB.communityItems

    operator fun invoke(): Unit = fireDB.readAllCommunityContent()
}