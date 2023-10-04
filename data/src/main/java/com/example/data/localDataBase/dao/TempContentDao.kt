package com.example.data.localDataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.localDataBase.table.TempContentTable

@Dao
interface TempContentDao {
    @Query("SELECT * FROM TempContent_Table ORDER BY TimeStamp ASC")
    fun readAllTemporaryContent(): List<TempContentTable>

    @Insert
    suspend fun saveTemporaryContent(content: TempContentTable)

    @Update
    suspend fun updateTemporaryContent(content: TempContentTable)

    @Delete
    suspend fun deleteTemporaryContent(content: TempContentTable)
}