package com.example.projecteyebrow.di

import android.content.Context
import androidx.room.Room
import com.example.projecteyebrow.database.AppDatabase
import com.example.projecteyebrow.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /* -- RoomDB Module(Singleton)-- */
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
    fun provideAppRepos(userDao: UserDao) = AppRepository(userDao)

}