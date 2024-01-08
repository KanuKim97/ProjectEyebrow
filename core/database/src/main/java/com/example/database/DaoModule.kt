package com.example.database

import com.example.database.dao.TempContentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideTempContentDao(
        tempContentDatabase: TempContentDB
    ): TempContentDao = tempContentDatabase.tempContentDao()
}