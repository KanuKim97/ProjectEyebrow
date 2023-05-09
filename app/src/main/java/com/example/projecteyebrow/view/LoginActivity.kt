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
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private val _binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val logInViewModel: LoginViewModel by viewModels()

    private val userEmail: String by lazy { setUserEmail() }
    private val userPassword: String by lazy { setUserPassword() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogInSuccess()

        _binding.LogInBtn.setOnClickListener(this)
        _binding.signInBtn.setOnClickListener(this)
        _binding.findPasswordBtn.setOnClickListener(this)

        setContentView(_binding.root)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.LogIn_Btn ->
                validateUserInput(userEmail, userPassword)
            R.id.signIn_Btn ->
                startActivity(Intent(this, SignInActivity::class.java))
            R.id.findPassword_Btn ->
                startActivity(Intent(this, FindPasswordActivity::class.java))
        }
    }

    private fun setUserEmail(): String = _binding.userEmailLoginInputField.text.toString()
    private fun setUserPassword(): String = _binding.userPasswordLoginInputField.text.toString()

    private fun isEmailPattern(userEmail: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()

    private fun signInUserAccount(userEmail: String, userPassword: String): Job =
        logInViewModel.signInUserAccount(userEmail, userPassword)

    private fun validateUserInput(userEmail: String, userPassword: String) {
        when {
            !(isEmailPattern(userEmail)) -> toastMessage.apply {
                setText(R.string.IsNotEmailPattern)
                duration = Toast.LENGTH_SHORT
            }.show()
            userEmail.isEmpty() -> toastMessage.apply {
                setText(R.string.InputUserEmail)
                duration = Toast.LENGTH_SHORT
            }.show()
            userPassword.isEmpty() -> toastMessage.apply {
                setText(R.string.InputUserPassword)
                duration = Toast.LENGTH_SHORT
            }.show()
            else -> signInUserAccount(userEmail, userPassword)
        }
    }

    private fun isLogInSuccess(): Unit =
        logInViewModel.logInTaskResult.observe(this) { result ->
            if (result.isSuccess) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                toastMessage.apply {
                    setText(R.string.FailedLogIn)
                    duration = Toast.LENGTH_SHORT
                }.show()
            }
        }

}