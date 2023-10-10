package com.example.domain.usecase.fireDB.profile

import com.example.domain.model.UserProfileModel
import com.example.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadUserProfileUseCase @Inject constructor(
    private val fireDBRepo: UserProfileRepository
) {
    operator fun invoke(): Flow<UserProfileModel> = fireDBRepo.loadUserProfile()
}