package com.example.projecteyebrow.di.flow.producer

import com.example.projecteyebrow.common.Constant
import com.example.projecteyebrow.database.dao.ContentDAO
import com.example.projecteyebrow.database.tables.TemporaryContentEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import java.sql.SQLDataException
import java.sql.SQLException
import javax.inject.Inject

class RoomProducer @Inject constructor(
    private val contentDAO: ContentDAO
) {
    fun readAllTemporaryContent(): Flow<List<TemporaryContentEntity>> = flow {
        emit(contentDAO.readAllTemporaryContent())
    }.catch {  exception ->
        when (exception) {
            is IOException -> emit(listOf())
            is SQLDataException -> emit(listOf())
            is ClassNotFoundException -> emit(listOf())
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < Constant.MAX_RETRY_ATTEMPT) {
            delay(Constant.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }

    fun saveTemporaryContent(content: TemporaryContentEntity): Flow<Result<Unit>> = flow {
        contentDAO.saveTemporaryContent(content)
        emit(Result.success(Unit))
    }.catch {  exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < Constant.MAX_RETRY_ATTEMPT) {
            delay(Constant.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }

    fun updateTemporaryContent(content: TemporaryContentEntity): Flow<Result<Unit>> = flow {
        contentDAO.updateTemporaryContent(content)
        emit(Result.success(Unit))
    }.catch {  exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < Constant.MAX_RETRY_ATTEMPT) {
            delay(Constant.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }

    fun deleteTemporaryContent(content: TemporaryContentEntity): Flow<Result<Unit>> = flow {
        contentDAO.deleteTemporaryContent(content)
        emit(Result.success(Unit))
    }.catch {  exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < Constant.MAX_RETRY_ATTEMPT) {
            delay(Constant.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }
}