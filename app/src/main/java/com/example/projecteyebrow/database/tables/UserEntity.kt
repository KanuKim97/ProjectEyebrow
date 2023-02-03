package com.example.projecteyebrow.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "ShopName")
    var shopName: String,
    @ColumnInfo(name = "ShopAddress")
    var shopAddress: String,
    @ColumnInfo(name = "ShopRating")
    var shopRating: Float
)