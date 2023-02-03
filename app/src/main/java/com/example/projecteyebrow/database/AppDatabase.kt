package com.example.projecteyebrow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projecteyebrow.database.dao.UserDao
import com.example.projecteyebrow.database.tables.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shopDao(): UserDao
}