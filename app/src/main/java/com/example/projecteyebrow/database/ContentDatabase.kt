package com.example.projecteyebrow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projecteyebrow.database.dao.ContentDAO
import com.example.projecteyebrow.database.tables.TemporaryContentEntity

@Database(entities = [TemporaryContentEntity::class], version = 1, exportSchema = false)
abstract class ContentDatabase: RoomDatabase() {
    abstract fun contentDAO(): ContentDAO
}