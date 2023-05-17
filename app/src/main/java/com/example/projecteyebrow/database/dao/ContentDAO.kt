package com.example.projecteyebrow.database.dao

import androidx.room.*
import com.example.projecteyebrow.database.tables.TemporaryContentEntity

@Dao
interface ContentDAO {
    @Query("SELECT * FROM TemporarySavedContent_Table ORDER BY TimeStamp ASC")
    fun readAllTemporaryContent(): List<TemporaryContentEntity>

    @Insert
    suspend fun saveTemporaryContent(content: TemporaryContentEntity)

    @Update
    suspend fun updateTemporaryContent(content: TemporaryContentEntity)

    @Delete
    suspend fun deleteTemporaryContent(content: TemporaryContentEntity)
}