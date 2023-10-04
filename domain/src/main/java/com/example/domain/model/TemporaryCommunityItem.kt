package com.example.domain.model

import android.net.Uri

data class TemporaryCommunityItem(
    var tempID: Int,
    var tempTitle: String? = null,
    var tempContent: String? = null,
    val tempImageUriList: List<Uri> = emptyList(),
    var timeStamp: String
)