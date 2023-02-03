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
import com.example.projecteyebrow.databinding.ActivitySignInBinding
import com.example.projecteyebrow.viewModel.SignInViewModel
import com.example.projecteyebrow.viewModel.factory.ViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var signInBinding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewmodels()

    private val userNickName: String by lazy { signInBinding.userName.text.toString() }
    private val userEmail: String by lazy { signInBinding.userEmail.text.toString() }
    private val userPassword: String by lazy { signInBinding.userPassword.text.toString() }
    private val confPassword: String by lazy { signInBinding.userPasswordConfirm.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)
    }

    override fun onResume() {
        super.onResume()

        signInViewModel.signInResult.observe(this) {
            if(it) { startActivity(Intent(this, MainActivity::class.java)) }
            else {
                Toast.makeText(this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show()
                signInBinding.userName.text.clear()
                signInBinding.userEmail.text.clear()
                signInBinding.userPassword.text.clear()
                signInBinding.userPasswordConfirm.text.clear()
            }
        }

        signInBinding.signInBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.LogIn_Btn -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.signInBtn -> validateUserInput()
        }
    }

    private fun validateUserInput() {
        when {
            userNickName.length > 15 -> {
                signInBinding.userName.error = "15자 이상 입니다."
                signInBinding.userName.text.clear()
            }
            !(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) -> {
                signInBinding.userEmail.error = "유효하지 않은 이메일 형식입니다."
                signInBinding.userEmail.text.clear()
            }
            userPassword != confPassword -> {
                signInBinding.userPasswordConfirm.error = "패스워드가 일치하지 않습니다."
                signInBinding.userPasswordConfirm.text.clear()
            }
            userNickName.isEmpty() -> signInBinding.userName.error = "닉네임을 입력해주세요."
            userEmail.isEmpty() -> signInBinding.userEmail.error = "이메일을 입력하세요."
            userPassword.isEmpty() -> signInBinding.userPassword.error = "비밀번호를 입력하세요."
            confPassword.isEmpty() -> signInBinding.userPasswordConfirm.error = "비밀번호 확인을 위해 입력해주세요."
            else -> signInViewModel.signInAccount(userEmail, userPassword)
        }
    }
}