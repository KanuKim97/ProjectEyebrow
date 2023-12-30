package com.example.network

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FireBaseAuthModule {
    @Provides
    fun provideFireAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()
}