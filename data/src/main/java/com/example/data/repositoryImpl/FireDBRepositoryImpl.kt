package com.example.data.repositoryImpl

import com.example.domain.entity.CommunityItem
import com.example.domain.entity.ProfileItem
import com.example.domain.repository.FireDBRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireDBRepositoryImpl @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireDB: FirebaseDatabase
): FireDBRepository {
    private val _userProfile = MutableStateFlow<ProfileItem?>(null)
    private val _communityItems = MutableStateFlow<ArrayList<CommunityItem>?>(null)

    override val userProfile: Flow<ProfileItem>
        get() = _userProfile.filterNotNull()
    override val communityItems: Flow<ArrayList<CommunityItem>>
        get() = _communityItems.filterNotNull()

    private val _userUID: String by lazy { fireAuth.currentUser?.uid.toString() }
    private val _community: String = "community"
    private val _profileRef: DatabaseReference by lazy { fireDB.reference.child(_userUID) }
    private val _communityRef: DatabaseReference by lazy { fireDB.reference.child(_community) }
    private var communityArrayList = ArrayList<CommunityItem>()
    private var eventListener: ValueEventListener? = null

    override suspend fun saveUserProfile(
        userEmail: String,
        userName: String
    ) {
       _profileRef.child("userEmail").setValue(userEmail).await()
       _profileRef.child("userName").setValue(userName).await()
    }

    override fun loadUserProfile() {
        eventListener = _profileRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _userProfile.value = ProfileItem(
                    userEmail = snapshot.child("userEmail").value.toString(),
                    userName = snapshot.child("userName").value.toString()
                )
            }

            override fun onCancelled(error: DatabaseError) { _userProfile.value = null }
        })
    }

    override fun readAllCommunityContent() {
        eventListener = _communityRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    communityArrayList.add(it.getValue(CommunityItem::class.java) as CommunityItem)
                }
                _communityItems.value = communityArrayList
            }

            override fun onCancelled(error: DatabaseError) { _communityItems.value = null }
        })
    }

    override suspend fun uploadCommunityContent(
        uploadTitle: String,
        uploadContent: String
    ) {
        _communityRef.push().setValue(
            CommunityItem(
                title = uploadTitle,
                content = uploadContent,
                userUID = _userUID
            )
        ).await()
    }

    override fun stopEventListener() {
        eventListener?.apply {
            _profileRef.removeEventListener(this)
            _communityRef.removeEventListener(this)
        }
    }
}