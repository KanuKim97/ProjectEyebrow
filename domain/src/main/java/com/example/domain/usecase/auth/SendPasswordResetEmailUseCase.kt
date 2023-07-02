package com.example.domain.usecase.auth

import com.example.domain.repository.FireAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendPasswordResetEmailUseCase @Inject constructor(
    private val fireAuth: FireAuthRepository
) {
    operator fun invoke(userEmail: String): Flow<Result<Unit>> =
        fireAuth.sendPasswordResetEmail(userEmail)
}