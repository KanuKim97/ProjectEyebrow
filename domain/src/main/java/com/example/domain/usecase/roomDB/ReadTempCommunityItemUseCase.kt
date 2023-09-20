package com.example.domain.usecase.roomDB

import com.example.domain.model.TemporaryCommunityItem
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadTempCommunityItemUseCase @Inject constructor(
    private val roomDB: RoomDBRepository
) {
    operator fun invoke(): Flow<List<TemporaryCommunityItem>> = roomDB.readAllTemporaryCommunityItem()
}