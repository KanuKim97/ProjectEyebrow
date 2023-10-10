package com.example.domain.usecase.auth

import com.example.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val authRepo: UserAuthRepository
) {
    operator fun invoke(): Flow<Result<Unit>> = authRepo.deleteAccount()
}