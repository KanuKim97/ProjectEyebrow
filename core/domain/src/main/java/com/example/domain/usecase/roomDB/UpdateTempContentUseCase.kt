package com.example.domain.usecase.roomDB

import com.example.domain.repository.RoomDBRepository
import com.example.model.TempContent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTempContentUseCase @Inject constructor(
    private val roomDBRepo: RoomDBRepository
) {
    operator fun invoke(
        content: TempContent
    ): Flow<Result<Unit>> = roomDBRepo.updateTempContent(content)
}