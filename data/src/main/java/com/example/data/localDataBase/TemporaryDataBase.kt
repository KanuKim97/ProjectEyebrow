package com.example.data.localDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.localDataBase.dao.TempContentDao
import com.example.data.localDataBase.table.TemporaryItemTable
import com.example.data.localDataBase.util.ImageConverter

@Database(entities = [TemporaryItemTable::class], version = 1, exportSchema = false)
@TypeConverters(ImageConverter::class)
abstract class TemporaryDataBase: RoomDatabase() {
    abstract fun tempContentDAO(): TempContentDao
}