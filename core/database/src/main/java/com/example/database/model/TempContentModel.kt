package com.example.database.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TempContent")
data class TempContentModel(
    @PrimaryKey(autoGenerate = true) var itemID: Int,
    @ColumnInfo(name = "Title") var title: String? = null,
    @ColumnInfo(name = "Content") var content: String? = null,
    @ColumnInfo(name = "ImageUriList") var imageUriList: List<Uri>,
    @ColumnInfo(name = "TimeStamp") var timeStamp: String
)
