package com.example.domain.model

import android.net.Uri

data class ContentModel(
    val title: String? = null,
    val content: String? = null,
    val imageUriList: List<Uri> = emptyList(),
    val userUID: String
)
