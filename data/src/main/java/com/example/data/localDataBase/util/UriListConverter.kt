package com.example.data.localDataBase.util

import android.net.Uri
import androidx.room.TypeConverter

class UriListConverter {
    @TypeConverter
    fun fromUriList(uriList: List<Uri>): String =
        uriList.joinToString(",") { it.toString() }

    @TypeConverter
    fun toUriList(uriString: String): List<Uri> =
        uriString.split(",").map { Uri.parse(it) }
}