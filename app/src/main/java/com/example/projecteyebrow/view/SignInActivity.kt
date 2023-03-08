package com.example.projecteyebrow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.ActivitySignInBinding
import com.example.projecteyebrow.viewModel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var signInBinding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        signInBinding.toLoginPage.setOnClickListener(this)
        signInBinding.signInBtn.setOnClickListener(this)
        setContentView(signInBinding.root)
    }

    private fun getUserEmail(): String = signInBinding.userEmailSignInInputField.text.toString()
    private fun getUserNickName(): String = signInBinding.nickNameSignInInputField.text.toString()
    private fun getUserPassword(): String = signInBinding.userPasswordSignInInputField.text.toString()
    private fun getConfPassword(): String = signInBinding.confPasswordSignInInputField.text.toString()

    override fun onClick(v: View?) {
        val userEmail: String = getUserEmail()
        val userNickName: String = getUserNickName()
        val userPassword: String = getUserPassword()
        val confPassword: String = getConfPassword()

        when(v?.id) {
            R.id.toLoginPage -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.signInBtn -> validateUserInput(userEmail, userNickName, userPassword, confPassword)
        }
    }
    
    private fun validateUserInput(
        userEmail: String,
        userNickName: String,
        userPassword: String,
        confPassword: String
    ) {
        when {
            userEmail.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) -> {
                signInBinding.userEmailSignInInputField.error = ""
                signInBinding.userEmailSignInInputField.text?.clear()
            }
            userNickName.isEmpty() || userNickName.length > 15 -> {
                signInBinding.nickNameSignInInputField.error = ""
                signInBinding.nickNameSignInInputField.text?.clear()
            }
            userPassword.isEmpty() -> {
                signInBinding.userPasswordSignInInputField.error = ""
                signInBinding.userPasswordSignInInputField.text?.clear()
            }
            confPassword.isEmpty() -> {
                signInBinding.confPasswordSignInInputField.error = ""
                signInBinding.confPasswordSignInInputField.text?.clear()
            }
            userPassword != confPassword -> {
                signInBinding.userPasswordSignInInputField.error = ""
                signInBinding.userPasswordSignInInputField.text?.clear()
            }
            else -> signInUserAccount(userEmail, userPassword)
        }
    }

    private fun signInUserAccount(userEmail: String, userPassword: String) {
        signInViewModel.signInUserAccount(userEmail, userPassword)

        signInViewModel.signInTaskResult.observe(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            } else { it.exception?.printStackTrace() }
        }
    }

}