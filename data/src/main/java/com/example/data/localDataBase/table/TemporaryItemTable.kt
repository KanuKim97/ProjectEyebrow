package com.example.data.localDataBase.table

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TempItem_Table")
data class TemporaryItemTable(
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

