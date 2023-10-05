package com.example.domain.repository

import android.net.Uri
import com.example.domain.model.ContentModel
import com.example.domain.model.UserProfileModel
import kotlinx.coroutines.flow.Flow

interface FireDBRepository {

    fun saveUserProfile(userEmail: String, userName: String): Flow<Result<Unit>>

    fun loadUserProfile(): Flow<UserProfileModel>

    /* Community Function */
    fun readAllContent(): Flow<ArrayList<ContentModel>>

    fun uploadContent(title: String, content: String, imageUriList: List<Uri>): Flow<Result<Unit>>
}