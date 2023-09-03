package com.example.domain.usecase.fireDB.profile

import com.example.domain.repository.FireDBRepository
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    suspend operator fun invoke(
        userEmail: String,
        userName: String
    ): Unit = fireDB.saveUserProfile(userEmail, userName)
}