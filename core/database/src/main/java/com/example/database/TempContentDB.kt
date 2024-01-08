package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.dao.TempContentDao
import com.example.database.model.TempContentModel
import com.example.database.util.UriListConverter

@Database(
    entities = [TempContentModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UriListConverter::class)
abstract class TempContentDB: RoomDatabase() {
    abstract fun tempContentDao(): TempContentDao
}