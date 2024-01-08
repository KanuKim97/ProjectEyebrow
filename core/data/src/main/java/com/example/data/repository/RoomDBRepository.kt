package com.example.data.repository

import com.example.model.TempContent
import kotlinx.coroutines.flow.Flow

interface RoomDBRepository {
    fun readAllTempContent(): Flow<List<TempContent>>

    fun saveTempContent(content: TempContent): Flow<Result<Unit>>

    fun updateTempContent(content: TempContent): Flow<Result<Unit>>

    fun deleteTempContent(content: TempContent): Flow<Result<Unit>>
}