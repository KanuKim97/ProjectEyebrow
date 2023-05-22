package com.example.projecteyebrow.di.flow.producer

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireDBProducer @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val firebaseDB: FirebaseDatabase
) {
    private val _userProfileInfo = MutableStateFlow<String?>(null)
    val userProfile: Flow<String> get() = _userProfileInfo.filterNotNull()
    private val _userCollection = MutableStateFlow(null)
    val userCollection get() = _userCollection.filterNotNull()


    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    private val _communityPath: String by lazy { "community" }

    private val _profileRef: DatabaseReference by lazy { firebaseDB.reference.child(_userUID) }
    private val _communityRef: DatabaseReference by lazy { firebaseDB.reference.child(_communityPath) }

    private var eventListener: ValueEventListener? = null

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
                snapshot.child("userEmail").value
                snapshot.child("userName").value
            }

            override fun onCancelled(error: DatabaseError) { _userProfileInfo.value = null }
        })
    }

    suspend fun saveUserCollection() {
        _profileRef.child("collection")
            .push()
            .setValue(null)
            .await()
    }

    fun loadUserCollection() {
        eventListener = _profileRef.child("collection")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    TODO("implement needed")
                }

                override fun onCancelled(error: DatabaseError) { _userCollection.value = null }
            })
    }

    fun stopEventListen() {
        eventListener?.apply {
            _profileRef.removeEventListener(this)
            _communityRef.removeEventListener(this)
        }
    }
}