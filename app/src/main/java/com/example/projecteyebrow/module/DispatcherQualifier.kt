package com.example.projecteyebrow.module

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class AppDispatcher(val appDispatcher: AppDispatcherValue)

enum class AppDispatcherValue { IO, MAIN }