package com.example.data.repositoryImpl

import android.net.Uri
import android.util.Log
import com.example.data.repository.CommunityRepository
import com.example.model.ContentModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
): CommunityRepository {
    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }

    override fun readAllContents(): Flow<ArrayList<ContentModel>> = callbackFlow {
        fireStore.collection("UsersContents")
            .get()
            .addOnSuccessListener { result -> Log.d("Data", "${result.documents}") }
            .addOnFailureListener { _ -> trySend(arrayListOf()) }

        awaitClose()
    }

    override fun uploadContent(
        title: String,
        content: String,
        imageUrlList: List<Uri>
    ): Flow<Result<Unit>> = callbackFlow {
        val contentModel = ContentModel(title, content, listOf(), _userUID)

        fireStore.collection("UsersContents")
            .add(contentModel)
            .addOnSuccessListener { trySend(Result.success(Unit)) }
            .addOnFailureListener { exception -> trySend(Result.failure(exception)) }

        awaitClose()
    }
}