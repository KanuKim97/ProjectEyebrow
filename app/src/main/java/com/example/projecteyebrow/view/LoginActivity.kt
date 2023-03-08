package com.example.projecteyebrow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.ActivityLoginBinding
import com.example.projecteyebrow.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var loginBinding: ActivityLoginBinding
    private val logInViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginBinding.LogInBtn.setOnClickListener(this)
        loginBinding.signInBtn.setOnClickListener(this)
        loginBinding.findPasswordBtn.setOnClickListener(this)
        setContentView(loginBinding.root)
    }

    override fun onClick(v: View?) {
        val userEmail: String = getUserEmail()
        val userPassword: String = getUserPassword()

        when(v?.id) {
            R.id.LogIn_Btn -> validateUserInput(userEmail, userPassword)
            R.id.signIn_Btn -> startActivity(Intent(this, SignInActivity::class.java))
            R.id.findPassword_Btn ->
                startActivity(Intent(this, FindPasswordActivity::class.java))
        }
    }

    private fun getUserEmail(): String = loginBinding.userEmailLoginInputField.text.toString()
    private fun getUserPassword(): String = loginBinding.userPasswordLoginInputField.text.toString()

    private fun validateUserInput(userEmail: String, userPassword: String) {
        when {
            !(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) -> {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
            userEmail.isEmpty() -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            userPassword.isEmpty() -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            else -> userLogin(userEmail, userPassword)
        }
    }

    private fun userLogin(userEmail: String, userPassword: String) {
        logInViewModel.logInUserAccount(userEmail, userPassword)

        logInViewModel.logInTaskResult.observe(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else { it.exception?.printStackTrace() }
        }
    }
}