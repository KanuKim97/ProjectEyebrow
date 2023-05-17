package com.example.projecteyebrow.di.module

import android.content.Context
import androidx.room.Room
import com.example.projecteyebrow.database.ContentDatabase
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
    fun createUserDBInstance(@ApplicationContext context: Context): ContentDatabase =
        Room.databaseBuilder(context, ContentDatabase::class.java, "ContentDB")
            .build()

    @Provides
    @Singleton
    fun provideDaoService(contentDB: ContentDatabase) = contentDB.contentDAO()
}