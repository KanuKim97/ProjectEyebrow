package com.example.projecteyebrow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.projecteyebrow.database.dao.ContentDAO
import com.example.projecteyebrow.database.tables.TemporaryContentEntity
import com.example.projecteyebrow.database.util.ImageConverter

@Database(entities = [TemporaryContentEntity::class], version = 1, exportSchema = false)
@TypeConverters(ImageConverter::class)
abstract class ContentDatabase: RoomDatabase() {
    abstract fun contentDAO(): ContentDAO
}