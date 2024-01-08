package com.example.domain.usecase.roomDB

import com.example.model.TempContent
import com.example.data.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTempContentUseCase @Inject constructor(
    private val roomDBRepo: com.example.data.repository.RoomDBRepository
) {
    operator fun invoke(
        content: TempContent
    ): Flow<Result<Unit>> = roomDBRepo.saveTempContent(content)
}