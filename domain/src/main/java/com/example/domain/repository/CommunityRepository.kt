package com.example.domain.repository

import com.example.domain.model.CommunityItem
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun readAllCommunityItems(): Flow<ArrayList<CommunityItem>>

    fun uploadCommunityItem(uploadTitle: String, uploadContent: String): Flow<Result<Unit>>
}