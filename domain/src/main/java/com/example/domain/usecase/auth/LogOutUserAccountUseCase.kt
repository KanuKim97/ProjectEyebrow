package com.example.domain.usecase.auth

import com.example.domain.repository.FireAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogOutUserAccountUseCase @Inject constructor(
  private val fireAuth: FireAuthRepository
) {
    operator fun invoke(): Flow<Boolean> = fireAuth.logOutUserAccount()
}