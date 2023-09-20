package com.example.data.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class CommunityEntity(
    val itemTitle: String,
    val itemContent: String,
    val uploadUserUID: String,
    val uploadTimeStamp: String
)
