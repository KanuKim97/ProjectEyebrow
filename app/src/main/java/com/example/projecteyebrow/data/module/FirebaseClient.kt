package com.example.projecteyebrow.data.module

import com.google.firebase.auth.FirebaseAuth

object FirebaseClient {
    private var fireAuthClient: FirebaseAuth? = null

    fun getAuthClient(): FirebaseAuth {
        if(fireAuthClient == null) { fireAuthClient = FirebaseAuth.getInstance() }
        return fireAuthClient!!
    }
}