package com.example.domain.usecase.auth

import com.example.domain.repository.FireAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserSessionUseCase @Inject constructor(
    private val fireAuth: FireAuthRepository
) {
    val currentSession: Flow<Boolean> = fireAuth.currentSession

    operator fun invoke(): Unit = fireAuth.getUserCurrentSession()
}