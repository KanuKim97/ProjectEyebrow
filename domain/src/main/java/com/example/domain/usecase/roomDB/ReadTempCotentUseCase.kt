package com.example.domain.usecase.roomDB

import com.example.domain.model.TempContentModel
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadTempCotentUseCase @Inject constructor(
    private val roomDB: RoomDBRepository
) {
    operator fun invoke(): Flow<List<TempContentModel>> = roomDB.readAllTempContent()
}