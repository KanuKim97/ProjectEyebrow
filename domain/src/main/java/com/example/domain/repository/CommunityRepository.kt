package com.example.domain.repository

import com.example.domain.model.ContentModel
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun readAllContents(): Flow<ArrayList<ContentModel>>

    fun uploadContent(uploadTitle: String, uploadContent: String): Flow<Result<Unit>>
}