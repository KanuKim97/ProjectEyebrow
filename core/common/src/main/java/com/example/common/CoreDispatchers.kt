package com.example.common

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class CoreDispatchers(val dispatcher: DispatcherValue)

enum class DispatcherValue { IO, DEFAULT }