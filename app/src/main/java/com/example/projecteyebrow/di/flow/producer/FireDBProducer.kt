package com.example.projecteyebrow.di.flow.producer

import android.provider.ContactsContract.Data
import com.example.projecteyebrow.view.viewItems.CommunityItems
import com.example.projecteyebrow.view.viewItems.ProfileItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class FireDBProducer @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val firebaseDB: FirebaseDatabase
) {
    private val _userProfileInfo = MutableStateFlow<ProfileItem?>(null)
    private val _communityItems = MutableStateFlow<ArrayList<CommunityItems>?>(null)
    val userProfile: Flow<ProfileItem> get() = _userProfileInfo.filterNotNull()
    val communityItems: Flow<ArrayList<CommunityItems>> = _communityItems.filterNotNull()

    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    private val _communityPath: String by lazy { "community" }

    private val _profileRef: DatabaseReference by lazy { firebaseDB.reference.child(_userUID) }
    private val _communityRef: DatabaseReference by lazy { firebaseDB.reference.child(_communityPath) }

    private var eventListener: ValueEventListener? = null

    //TODO("saveUserProfile 함수 개선 필요")
    suspend fun saveUserProfile(
        userEmail: String,
        userName: String
    ) {
        _profileRef.child("userEmail").setValue(userEmail).await()
        _profileRef.child("userName").setValue(userName).await()
    }

    fun loadUserProfile() {
        eventListener = _profileRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _userProfileInfo.value = ProfileItem(
                    snapshot.child("userEmail").value.toString(),
                    snapshot.child("userName").value.toString()
                )
            }

            override fun onCancelled(error: DatabaseError) { _userProfileInfo.value = null }
        })
    }


    suspend fun uploadCommunityContent(
        uploadTitle: String,
        uploadContent: String
    ) {
        _communityRef.push().setValue(
            CommunityItems(
                uploadTitle,
                uploadContent,
                _userUID
            )
        ).await()
    }

    //TODO("readCommunityContent 함수 작성 필요")
    fun readCommunityContent() {
        eventListener = _communityRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {  }

            override fun onCancelled(error: DatabaseError) { _communityItems.value = null }
        })
    }

    fun stopEventListen() {
        eventListener?.apply {
            _profileRef.removeEventListener(this)
            _communityRef.removeEventListener(this)
        }
    }
}