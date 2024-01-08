package com.example.domain.usecase.auth

import com.example.data.repository.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendPWDResetEmailUseCase @Inject constructor(
    private val authRepo: com.example.data.repository.UserAuthRepository
) {
    operator fun invoke(userEmail: String): Flow<Result<Unit>> =
        authRepo.sendPWDResetEmail(userEmail)
}