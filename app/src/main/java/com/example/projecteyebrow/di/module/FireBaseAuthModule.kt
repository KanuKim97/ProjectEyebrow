package com.example.projecteyebrow.di.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FireBaseAuthModule {
    @Provides
    @Singleton
    fun providesFireAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()
}