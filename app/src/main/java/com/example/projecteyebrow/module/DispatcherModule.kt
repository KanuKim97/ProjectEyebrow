package com.example.projecteyebrow.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/* Coroutine Dispatcher Module (Main, IO) */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @AppDispatcher(AppDispatcherValue.IO)
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @AppDispatcher(AppDispatcherValue.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}