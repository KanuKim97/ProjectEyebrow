package com.example.domain.usecase.fireDB.profile

import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val fireDBRepo: FireDBRepository
) {
    operator fun invoke(
        userEmail: String,
        userName: String
    ): Flow<Result<Unit>> = fireDBRepo.saveUserProfile(userEmail, userName)
}