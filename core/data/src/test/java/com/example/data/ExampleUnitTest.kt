package com.example.data

import com.example.data.repositoryImpl.UserAuthRepositoryImpl
import com.example.domain.repository.UserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var authRepo: UserAuthRepository

    @Before
    fun setUpInstance() {
        fireBaseAuth = FirebaseAuth.getInstance()
        authRepo = UserAuthRepositoryImpl(fireBaseAuth)
    }

}