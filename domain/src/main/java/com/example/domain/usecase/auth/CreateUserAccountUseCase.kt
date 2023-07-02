package com.example.domain.usecase.auth

import com.example.domain.repository.FireAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserAccountUseCase @Inject constructor(
    private val fireAuth: FireAuthRepository
) {
    operator fun invoke(
        userEmail: String,
        userPassword: String
    ): Flow<Result<Unit>> = fireAuth.createUserAccount(userEmail, userPassword)
}