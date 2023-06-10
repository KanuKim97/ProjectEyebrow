package com.example.domain.repository

import com.example.domain.entity.CommunityItem
import com.example.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface FireDBRepository {
    val userProfile: Flow<ProfileItem>
    val communityItems: Flow<ArrayList<CommunityItem>>

    suspend fun saveUserProfile(userEmail: String, userName: String)

    fun loadUserProfile()

    fun readAllCommunityContent()

    suspend fun uploadCommunityContent(uploadTitle: String, uploadContent: String)

    fun stopEventListener()
}