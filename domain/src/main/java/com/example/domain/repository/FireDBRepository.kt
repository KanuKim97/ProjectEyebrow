package com.example.domain.repository

import com.example.domain.entity.CommunityItem
import com.example.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface FireDBRepository {
    /* UserProfile Function */
    val userProfile: Flow<ProfileItem>

    suspend fun saveUserProfile(userEmail: String, userName: String)

    fun loadUserProfile()

    /* Community Function */
    val communityItems: Flow<ArrayList<CommunityItem>>

    fun readAllCommunityContent()

    suspend fun uploadCommunityContent(uploadTitle: String, uploadContent: String)

    /* Collection Function */


    /* FireBase DataBase EventListener Function */
    fun stopEventListener()
}