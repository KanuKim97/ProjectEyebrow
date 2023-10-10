package com.example.domain.repository

import com.example.domain.model.UserProfileModel
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun saveUserProfile(userEmail: String, userName: String): Flow<Result<Unit>>

    fun loadUserProfile(): Flow<UserProfileModel>
}