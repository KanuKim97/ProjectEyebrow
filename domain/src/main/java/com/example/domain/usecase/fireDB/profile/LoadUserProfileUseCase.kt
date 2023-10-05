package com.example.domain.usecase.fireDB.profile

import com.example.domain.model.UserProfileModel
import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadUserProfileUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    operator fun invoke(): Flow<UserProfileModel> = fireDB.loadUserProfile()
}