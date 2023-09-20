package com.example.projecteyebrow.module

import com.example.data.repositoryImpl.FireAuthRepositoryImpl
import com.example.data.repositoryImpl.FireDBRepositoryImpl
import com.example.domain.repository.FireAuthRepository
import com.example.domain.repository.FireDBRepository
import com.example.projecteyebrow.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideUserDB(): FirebaseDatabase =
        FirebaseDatabase.getInstance(BuildConfig.Tomorrow_UserDB_URL)

    @Provides
    @Singleton
    fun provideCommunityDBRef(): DatabaseReference =
        FirebaseDatabase.getInstance(BuildConfig.Tomorrow_CommunityDB_URL).reference

    @Provides
    @Singleton
    fun provideFireAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideFireAuthRepoImpl(fireAuth: FirebaseAuth): FireAuthRepository =
        FireAuthRepositoryImpl(fireAuth)

    @Provides
    @Singleton
    fun provideFireDBRepoImpl(
        fireAuth: FirebaseAuth,
        userDB: FirebaseDatabase,
        communityRef: DatabaseReference
    ): FireDBRepository = FireDBRepositoryImpl(fireAuth, userDB, communityRef)
}