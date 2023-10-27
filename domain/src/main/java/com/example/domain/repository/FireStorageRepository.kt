package com.example.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface FireStorageRepository {
    fun uploadImages(imageList: List<Uri>): Flow<Result<Unit>>
}