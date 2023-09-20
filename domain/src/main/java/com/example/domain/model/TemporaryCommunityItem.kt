package com.example.domain.model

import android.graphics.Bitmap

data class TemporaryCommunityItem(
    var contentID: Int,
    var title: String? = null,
    var content: String? = null,
    var contentImage: Bitmap? = null,
    var contentTimeStamp: String
)