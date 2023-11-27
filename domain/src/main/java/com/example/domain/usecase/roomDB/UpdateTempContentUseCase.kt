package com.example.domain.usecase.roomDB

import com.example.domain.model.TempContentModel
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTempContentUseCase @Inject constructor(
    private val roomDBRepo: RoomDBRepository
) {
    operator fun invoke(
        content: TempContentModel
    ): Flow<Result<Unit>> = roomDBRepo.updateTempContent(content)
}