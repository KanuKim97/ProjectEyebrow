package com.example.domain.usecase.roomDB

import com.example.domain.model.TempContentModel
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTempContentUseCase @Inject constructor(
    private val roomDB: RoomDBRepository
) {
    operator fun invoke(
        content: TempContentModel
    ): Flow<Result<Unit>> = roomDB.deleteTempContent(content)
}