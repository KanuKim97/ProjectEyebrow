package com.example.data.localDataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.localDataBase.table.TemporaryItemTable

@Dao
interface TempContentDao {
    @Query("SELECT * FROM TempItem_Table ORDER BY TimeStamp ASC")
    fun readAllTemporaryContent(): List<TemporaryItemTable>

    @Insert
    suspend fun saveTemporaryContent(content: TemporaryItemTable)

    @Update
    suspend fun updateTemporaryContent(content: TemporaryItemTable)

    @Delete
    suspend fun deleteTemporaryContent(content: TemporaryItemTable)
}