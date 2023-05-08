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
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private val _binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }
    private val signInViewModel: SignInViewModel by viewModels()

    private val userEmail: String by lazy { setUserEmail() }
    private val userNickName: String by lazy { setUserNickName() }
    private val userPassword: String by lazy { setUserPassword() }
    private val confPassword: String by lazy { setConfPassword() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCreateSuccess()
        _binding.toLoginPage.setOnClickListener(this)
        _binding.signInBtn.setOnClickListener(this)

        setContentView(_binding.root)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.toLoginPage -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.signInBtn -> validateUserInput(userEmail, userNickName, userPassword, confPassword)
        }
    }

    private fun setUserEmail(): String = _binding.userEmailSignInInputField.text.toString()
    private fun setUserNickName(): String = _binding.nickNameSignInInputField.text.toString()
    private fun setUserPassword(): String = _binding.userPasswordSignInInputField.text.toString()
    private fun setConfPassword(): String = _binding.confPasswordSignInInputField.text.toString()

    private fun createUserAccount(userEmail: String, userPassword: String): Job =
        signInViewModel.createUserAccount(userEmail, userPassword)

    private fun isCreateSuccess(): Unit =
        signInViewModel.signInTaskResult.observe(this) { result ->
            if (result.isSuccess) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                toastMessage.apply {
                    duration = Toast.LENGTH_SHORT
                }.show()
            }
        }

    private fun isEmailPattern(userEmail: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()

    private fun validateUserInput(
        userEmail: String,
        userNickName: String,
        userPassword: String,
        confPassword: String
    ) {
        when {
            userEmail.isEmpty() || isEmailPattern(userEmail) -> {
                _binding.userEmailSignInInputField.error = ""
            }
            userNickName.isEmpty() || userNickName.length > 15 -> {
                _binding.nickNameSignInInputField.error = ""
            }
            userPassword.isEmpty() -> {
                _binding.userPasswordSignInInputField.error = ""
            }
            confPassword.isEmpty() -> {
                _binding.confPasswordSignInInputField.error = ""
            }
            userPassword != confPassword -> {
                _binding.userPasswordSignInInputField.error = ""
            }
            else -> createUserAccount(userEmail, userPassword)
        }
    }

}