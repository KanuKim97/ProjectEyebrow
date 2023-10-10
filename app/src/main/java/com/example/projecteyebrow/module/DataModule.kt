package com.example.projecteyebrow.module

import com.example.data.repositoryImpl.CommunityRepositoryImpl
import com.example.data.repositoryImpl.UserAuthRepositoryImpl
import com.example.data.repositoryImpl.UserProfileRepositoryImpl
import com.example.domain.repository.CommunityRepository
import com.example.domain.repository.UserAuthRepository
import com.example.domain.repository.UserProfileRepository
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
    fun provideFireAuthRepoImpl(fireAuth: FirebaseAuth): UserAuthRepository =
        UserAuthRepositoryImpl(fireAuth)

    @Provides
    @Singleton
    fun provideFireDBRepoImpl(
        fireAuth: FirebaseAuth,
        userDB: FirebaseDatabase
    ): UserProfileRepository = UserProfileRepositoryImpl(fireAuth, userDB)

    @Provides
    @Singleton
    fun provideCommunityRepoImpl(
        fireAuth: FirebaseAuth,
        communityRef: DatabaseReference
    ): CommunityRepository = CommunityRepositoryImpl(fireAuth, communityRef)
}