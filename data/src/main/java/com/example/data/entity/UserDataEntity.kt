package com.example.data.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDataEntity(val userName: String, val userEmail: String)
