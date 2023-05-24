package com.example.projecteyebrow.di.module

import com.example.projecteyebrow.BuildConfig
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireBaseDBModule {
    @Provides
    @Singleton
    fun provideFireBaseDBInstance(): FirebaseDatabase =
        FirebaseDatabase.getInstance(BuildConfig.Firebase_Database_URL)
}