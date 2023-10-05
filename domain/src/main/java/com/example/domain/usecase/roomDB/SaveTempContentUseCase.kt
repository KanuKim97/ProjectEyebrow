package com.example.domain.usecase.roomDB

import com.example.domain.model.TempContentModel
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTempContentUseCase @Inject constructor(
    private val roomDB: RoomDBRepository
) {
    operator fun invoke(
        content: TempContentModel
    ): Flow<Result<Unit>> = roomDB.saveTempContent(content)
}