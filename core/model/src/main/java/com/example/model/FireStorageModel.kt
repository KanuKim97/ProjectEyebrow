package com.example.model

import java.net.URI

data class UserProfileModel(
    val userName: String,
    val userEmail: String
)

data class ContentModel(
    val title: String? = null,
    val content: String? = null,
    val imgUriList: List<URI> = emptyList(),
    val userID: String
)

data class CommunityModel(
    val itemTitle: String,
    val itemContent: String,
    val uploadUserUID: String,
    val uploadTimeStamp: String
)

data class TempContentModel(
    var tempID: Int,
    var tempTitle: String? = null,
    var tempContent: String? = null,
    val tempImgUriList: List<URI> = emptyList(),
    var timeStamp: String
)