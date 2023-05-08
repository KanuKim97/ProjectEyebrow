package com.example.projecteyebrow.di.module

import android.content.Context
import androidx.room.Room
import com.example.projecteyebrow.database.AppDatabase
import com.example.projecteyebrow.database.dao.UserDao
import com.example.projecteyebrow.di.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun createUserDBInstance(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "DB_ver1")
            .build()

    @Provides
    @Singleton
    fun provideDaoService(userDB: AppDatabase) = userDB.shopDao()

    @Provides
    @Singleton
    fun provideUserDBRepository(userDao: UserDao) = RoomRepository(userDao)
}