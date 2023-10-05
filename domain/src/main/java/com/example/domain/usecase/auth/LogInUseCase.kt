package com.example.domain.usecase.auth

import com.example.domain.repository.FireAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authRepo: FireAuthRepository
) {
    operator fun invoke(
        userEmail: String,
        userPassword: String
    ): Flow<Result<Unit>> = authRepo.logIn(userEmail, userPassword)
}