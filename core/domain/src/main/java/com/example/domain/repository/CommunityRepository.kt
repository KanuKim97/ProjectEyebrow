package com.example.domain.repository

import android.net.Uri
import com.example.model.ContentModel
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun readAllContents(): Flow<ArrayList<ContentModel>>

    fun uploadContent(title: String, content: String, imageUrlList: List<Uri>): Flow<Result<Unit>>
}