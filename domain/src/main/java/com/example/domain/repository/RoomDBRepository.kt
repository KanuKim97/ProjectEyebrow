package com.example.domain.repository

import com.example.domain.entity.TemporaryCommunityItem
import kotlinx.coroutines.flow.Flow

interface RoomDBRepository {
    fun readAllTemporaryCommunityItem(): Flow<List<TemporaryCommunityItem>>

    fun saveTemporaryCommunityItem(content: TemporaryCommunityItem): Flow<Result<Unit>>

    fun updateTemporaryCommunityItem(content: TemporaryCommunityItem): Flow<Result<Unit>>

    fun deleteTemporaryCommunityItem(content: TemporaryCommunityItem): Flow<Result<Unit>>
}