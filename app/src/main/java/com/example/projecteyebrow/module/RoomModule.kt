package com.example.projecteyebrow.module

import android.content.Context
import androidx.room.Room
import com.example.data.localDataBase.TemporaryDataBase
import com.example.data.localDataBase.dao.TempContentDao
import com.example.data.repositoryImpl.RoomDBRepositoryImpl
import com.example.domain.repository.RoomDBRepository
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
    fun createUserDBInstance(@ApplicationContext context: Context): TemporaryDataBase =
        Room.databaseBuilder(context, TemporaryDataBase::class.java, "TempDB")
            .build()

    @Provides
    @Singleton
    fun provideDaoService(tempDB: TemporaryDataBase): TempContentDao = tempDB.tempContentDAO()

    @Provides
    @Singleton
    fun provideRoomDBRepositoryImpl(tempContentDao: TempContentDao): RoomDBRepository =
        RoomDBRepositoryImpl(tempContentDao)
}