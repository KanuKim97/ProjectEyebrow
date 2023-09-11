package com.example.domain.repository

import com.example.domain.entity.CommunityItem
import com.example.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface FireDBRepository {

    fun saveUserProfile(userEmail: String, userName: String): Flow<Result<Unit>>

    fun loadUserProfile(): Flow<ProfileItem>

    /* Community Function */
    fun readAllCommunityContent(): Flow<ArrayList<CommunityItem>>

    fun uploadCommunityContent(uploadTitle: String, uploadContent: String): Flow<Result<Unit>>

    /* FireBase DataBase EventListener Function */
    fun stopEventListener()
}