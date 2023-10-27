package com.example.data.repositoryImpl

import android.net.Uri
import com.example.domain.repository.FireStorageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FireStorageRepositoryImpl @Inject constructor(
    private val fireStorage: FirebaseStorage
): FireStorageRepository {
    override fun uploadImages(imageList: List<Uri>): Flow<Result<Unit>> = callbackFlow {  }

}