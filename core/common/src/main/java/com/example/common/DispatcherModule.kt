package com.example.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    fun provideIODispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideDefaultDispatchers(): CoroutineDispatcher = Dispatchers.Default

}