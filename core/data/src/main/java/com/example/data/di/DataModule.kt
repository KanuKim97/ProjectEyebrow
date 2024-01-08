package com.example.data.di

import com.example.data.repository.CommunityRepository
import com.example.data.repository.RoomDBRepository
import com.example.data.repository.UserAuthRepository
import com.example.data.repository.UserProfileRepository
import com.example.data.repositoryImpl.CommunityRepositoryImpl
import com.example.data.repositoryImpl.RoomDBRepositoryImpl
import com.example.data.repositoryImpl.UserAuthRepositoryImpl
import com.example.data.repositoryImpl.UserProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindProfileRepos(
        profileRepo: UserProfileRepositoryImpl
    ): UserProfileRepository

    @Binds
    internal abstract fun bindsCommunityRepos(
        communityRepository: CommunityRepositoryImpl
    ): CommunityRepository

    @Binds
    internal abstract fun bindsRoomDBRepos(
        roomDBRepository: RoomDBRepositoryImpl
    ): RoomDBRepository

    @Binds
    internal abstract fun bindAuthRepos(
        authRepository: UserAuthRepositoryImpl
    ): UserAuthRepository
}