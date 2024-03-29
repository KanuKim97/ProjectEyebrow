package com.example.data.repositoryImpl

import com.example.data.localDataBase.dao.TempContentDao
import com.example.data.util.mappingToListTempCommunityItem
import com.example.data.util.mappingToTempCommunityTable
import com.example.domain.entity.TemporaryCommunityItem
import com.example.domain.repository.RoomDBRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import java.sql.SQLDataException
import javax.inject.Inject

class RoomDBRepositoryImpl @Inject constructor(
    private val tempDao: TempContentDao
): RoomDBRepository {
    override fun readAllTemporaryCommunityItem(): Flow<List<TemporaryCommunityItem>> = flow {
        val result: List<TemporaryCommunityItem> =
            mappingToListTempCommunityItem(tempDao.readAllTemporaryContent())
        emit(result)
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(listOf())
            is SQLDataException -> emit(listOf())
            is ClassNotFoundException -> emit(listOf())
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLDataException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }

    override fun saveTemporaryCommunityItem(
        content: TemporaryCommunityItem
    ): Flow<Result<Unit>> = flow {
        tempDao.saveTemporaryContent(content = mappingToTempCommunityTable(content))
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLDataException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }

    override fun updateTemporaryCommunityItem(
        content: TemporaryCommunityItem
    ): Flow<Result<Unit>> = flow {
        tempDao.updateTemporaryContent(content = mappingToTempCommunityTable(content))
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLDataException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }

    override fun deleteTemporaryCommunityItem(
        content: TemporaryCommunityItem
    ): Flow<Result<Unit>> = flow {
        tempDao.deleteTemporaryContent(content = mappingToTempCommunityTable(content))
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLDataException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }
}