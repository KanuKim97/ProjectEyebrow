package com.example.domain.usecase.roomDB

import com.example.model.TempContent
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTempContentUseCase @Inject constructor(
    private val roomDBRepo: RoomDBRepository
) {
    operator fun invoke(
        content: TempContent
    ): Flow<Result<Unit>> = roomDBRepo.saveTempContent(content)
}