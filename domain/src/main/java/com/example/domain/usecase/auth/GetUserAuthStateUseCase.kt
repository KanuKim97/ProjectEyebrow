package com.example.domain.usecase.auth

import com.example.domain.repository.FireAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAuthStateUseCase @Inject constructor(
    private val authRepo: FireAuthRepository
) {
    operator fun invoke(): Flow<Boolean> = authRepo.getUserAuthState()
}