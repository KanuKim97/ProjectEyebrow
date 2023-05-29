package com.example.projecteyebrow.database.tables

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TemporarySavedContent_Table")
data class TemporaryContentEntity(
    @PrimaryKey(autoGenerate = true)
    var contentID: Int,
    @ColumnInfo(name = "Title")
    var contentTitle: String? = null,
    @ColumnInfo(name = "Content")
    var TemporaryContent: String? = null,
    @ColumnInfo(name = "Image")
    var TemporaryContentImage: Bitmap? = null,
    @ColumnInfo(name = "TimeStamp")
    var contentTime: String
)
