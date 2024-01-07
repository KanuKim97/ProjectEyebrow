package com.example.domain.usecase.roomDB

import com.example.domain.repository.RoomDBRepository
import com.example.model.TempContent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadTempContentUseCase @Inject constructor(
    private val roomDBRepo: RoomDBRepository
) {
    operator fun invoke(): Flow<List<TempContent>> = roomDBRepo.readAllTempContent()
}