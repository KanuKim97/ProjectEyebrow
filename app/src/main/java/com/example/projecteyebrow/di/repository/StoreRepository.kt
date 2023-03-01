package com.example.projecteyebrow.di.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/* -- FireBase Cloud Store Repository -- */
class StoreRepository @Inject constructor(
    private val firebaseStore: FirebaseFirestore
) {

}