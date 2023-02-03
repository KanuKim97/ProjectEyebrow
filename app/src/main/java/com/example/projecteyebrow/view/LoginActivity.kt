package com.example.projecteyebrow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projecteyebrow.R
import com.example.projecteyebrow.di.AppRepository
import com.example.projecteyebrow.databinding.ActivityLoginBinding
import com.example.projecteyebrow.viewModel.LoginViewModel
import com.example.projecteyebrow.viewModel.factory.ViewModelFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var loginViewModel: LoginViewModel

    private val userID: String by lazy { loginBinding.userNameLogin.text.toString() }
    private val userPassword: String by lazy { loginBinding.userPasswordLogin.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        vmFactory = ViewModelFactory(AppRepository())
        loginViewModel = ViewModelProvider(this, vmFactory) [LoginViewModel::class.java]
        setContentView(loginBinding.root)
    }

    override fun onResume() {
        super.onResume()

        loginViewModel.logInData.observe(this) {
            if (it) { startActivity(Intent(this, MainActivity::class.java)) }
            else { Toast.makeText(this, "아이디와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show() }
        }

        loginBinding.LogInBtn.setOnClickListener(this)
        loginBinding.signInBtn.setOnClickListener(this)
        loginBinding.findPasswordBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.LogIn_Btn -> validateUserInput(userEmail = userID, userPassword = userPassword)
            R.id.signInBtn ->
                startActivity(Intent(this, SignInActivity::class.java))
            R.id.findPassword_Btn ->
                startActivity(Intent(this, FindPasswordActivity::class.java))
        }
    }

    private fun validateUserInput(userEmail: String, userPassword: String) {
        when {
            !(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) -> {
                Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_LONG).show()
            }
            (userEmail.isEmpty() && userPassword.isEmpty()) -> {
                Toast.makeText(this, "이메일과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show()
            }
            userEmail.isEmpty() -> {
                Toast.makeText(this, "이메일을 입력하세요.", Toast.LENGTH_LONG).show()
            }
            userPassword.isEmpty() -> {
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show()
            }
            else -> { loginViewModel.logInUser(userEmail, userPassword) }
        }
    }
}