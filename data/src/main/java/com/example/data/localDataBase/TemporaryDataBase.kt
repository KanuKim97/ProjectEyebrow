package com.example.data.localDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.localDataBase.dao.TempContentDao
import com.example.data.localDataBase.table.TempContentTable
import com.example.data.localDataBase.util.UriListConverter

@Database(entities = [TempContentTable::class], version = 1, exportSchema = false)
@TypeConverters(UriListConverter::class)
abstract class TemporaryDataBase: RoomDatabase() {
    abstract fun tempContentDAO(): TempContentDao
}