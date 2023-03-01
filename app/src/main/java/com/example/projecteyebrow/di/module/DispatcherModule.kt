package com.example.projecteyebrow.di.module

import com.example.projecteyebrow.di.coroutineDispatcher.DefaultDispatcher
import com.example.projecteyebrow.di.coroutineDispatcher.IoDispatcher
import com.example.projecteyebrow.di.coroutineDispatcher.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

/* Coroutine Dispatcher Module (Main, IO, Default) */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @IoDispatcher
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher() = Dispatchers.Main

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher() = Dispatchers.Default
}