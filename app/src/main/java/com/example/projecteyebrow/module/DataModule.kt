package com.example.projecteyebrow.module

import android.content.Context
import androidx.room.Room
import com.example.data.localDataBase.TemporaryDataBase
import com.example.data.localDataBase.dao.TempContentDao
import com.example.data.repositoryImpl.CommunityRepositoryImpl
import com.example.data.repositoryImpl.FireStorageRepositoryImpl
import com.example.data.repositoryImpl.RoomDBRepositoryImpl
import com.example.data.repositoryImpl.UserAuthRepositoryImpl
import com.example.data.repositoryImpl.UserProfileRepositoryImpl
import com.example.domain.repository.CommunityRepository
import com.example.domain.repository.RoomDBRepository
import com.example.domain.repository.UserAuthRepository
import com.example.domain.repository.UserProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideFireAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFireStorageInstance(): FirebaseStorage = Firebase.storage

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

    @Provides
    @Singleton
    fun provideFireAuthRepoImpl(fireAuth: FirebaseAuth): UserAuthRepository =
        UserAuthRepositoryImpl(fireAuth)

    @Provides
    @Singleton
    fun provideFireDBRepoImpl(
        fireAuth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): UserProfileRepository = UserProfileRepositoryImpl(fireAuth, fireStore)

    @Provides
    @Singleton
    fun provideCommunityRepoImpl(
        fireAuth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): CommunityRepository = CommunityRepositoryImpl(fireAuth, fireStore)

    @Provides
    @Singleton
    fun provideFireStorageImpl(fireStorage: FirebaseStorage): FireStorageRepositoryImpl =
        FireStorageRepositoryImpl(fireStorage)
}