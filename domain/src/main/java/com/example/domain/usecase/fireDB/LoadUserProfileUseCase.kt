package com.example.domain.usecase.fireDB

import com.example.domain.entity.ProfileItem
import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadUserProfileUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    val userProfile: Flow<ProfileItem> = fireDB.userProfile

    operator fun invoke(): Unit = fireDB.loadUserProfile()
}