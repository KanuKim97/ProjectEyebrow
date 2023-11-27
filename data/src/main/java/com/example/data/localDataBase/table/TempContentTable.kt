package com.example.data.localDataBase.table

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TempContent_Table")
data class TempContentTable(
    @PrimaryKey(autoGenerate = true) var itemID: Int,
    @ColumnInfo(name = "Title") var title: String? = null,
    @ColumnInfo(name = "Content") var content: String? = null,
    @ColumnInfo(name = "ImageUriList") var imageUriList: List<Uri>,
    @ColumnInfo(name = "TimeStamp") var timeStamp: String
)

