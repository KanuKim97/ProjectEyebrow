package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.database.model.TempContentModel

@Dao
interface TempContentDao {
    @Query("SELECT * FROM TempContent ORDER BY TimeStamp ASC")
    fun readAllTemporaryContent(): List<TempContentModel>

    @Insert
    suspend fun saveTemporaryContent(content: TempContentModel)

    @Update
    suspend fun updateTemporaryContent(content: TempContentModel)

    @Delete
    suspend fun deleteTemporaryContent(content: TempContentModel)

}