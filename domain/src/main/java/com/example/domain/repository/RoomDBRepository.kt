package com.example.domain.repository

import com.example.domain.model.TempContentModel
import kotlinx.coroutines.flow.Flow

interface RoomDBRepository {
    fun readAllTempContent(): Flow<List<TempContentModel>>

    fun saveTempContent(content: TempContentModel): Flow<Result<Unit>>

    fun updateTempContent(content: TempContentModel): Flow<Result<Unit>>

    fun deleteTempContent(content: TempContentModel): Flow<Result<Unit>>
}