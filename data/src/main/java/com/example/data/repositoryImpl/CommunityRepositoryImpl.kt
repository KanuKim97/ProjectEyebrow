package com.example.data.repositoryImpl

import com.example.data.entity.CommunityEntity
import com.example.domain.model.ContentModel
import com.example.domain.repository.CommunityRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val communityDBRef: DatabaseReference
): CommunityRepository {
    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    private val _currTimeStamp: String = LocalDateTime.now().toString()
    override fun readAllContents(): Flow<ArrayList<ContentModel>> = flow {

    }

    override fun uploadContent(
        uploadTitle: String,
        uploadContent: String
    ): Flow<Result<Unit>> = flow {
        val uploadRequest = communityDBRef.push().setValue(
            CommunityEntity(
                itemTitle = uploadTitle,
                itemContent = uploadContent,
                uploadUserUID = _userUID,
                uploadTimeStamp = _currTimeStamp
            )
        )

        if (uploadRequest.isSuccessful) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception()))
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(IOException()))
            else -> throw exception
        }
    }
}