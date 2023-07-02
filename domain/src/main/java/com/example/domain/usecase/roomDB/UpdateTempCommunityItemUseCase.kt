package com.example.domain.usecase.roomDB

import com.example.domain.entity.TemporaryCommunityItem
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTempCommunityItemUseCase @Inject constructor(
    private val roomDB: RoomDBRepository
) {
    operator fun invoke(
        content: TemporaryCommunityItem
    ): Flow<Result<Unit>> = roomDB.updateTemporaryCommunityItem(content)
}