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
import com.example.projecteyebrow.databinding.ActivityFindPasswordBinding
import com.example.projecteyebrow.viewModel.FindPasswordViewModel
import com.example.projecteyebrow.viewModel.factory.ViewModelFactory

class FindPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var findPasswordBinding: ActivityFindPasswordBinding
    private lateinit var findPasswordViewModel: FindPasswordViewModel
    private lateinit var vmFactory: ViewModelFactory

    private val userEmail : String by lazy { findPasswordBinding.userEmail.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPasswordBinding = ActivityFindPasswordBinding.inflate(layoutInflater)
        vmFactory = ViewModelFactory(AppRepository())
        findPasswordViewModel = ViewModelProvider(this, vmFactory)[FindPasswordViewModel::class.java]

        setContentView(R.layout.activity_find_password)
    }

    override fun onResume() {
        super.onResume()

        findPasswordViewModel.emailResetValue.observe(this) {
            if(it) {
                Toast.makeText(
                    this,
                    userEmail + "로 비밀번호 초기화 메일이 전송되었습니다. \n확인해주세요.",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                Toast.makeText(
                    this,
                    userEmail + "로 비밀번호 초기화 메일이 전송이 실패했습니다.",
                    Toast.LENGTH_LONG
                ).show()
                findPasswordBinding.userEmail.text.clear()
            }
        }

        findPasswordBinding.resetEmailBtn.setOnClickListener(this)
        findPasswordBinding.toLoginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.toLogin_Btn -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.resetEmail_Btn -> validateUserEmail()
        }
    }

    private fun validateUserEmail() {
        when {
            userEmail.isEmpty() -> findPasswordBinding.userEmail.error = "이메일을 입력해주세요"
            !(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) -> {
                findPasswordBinding.userEmail.error = "이메일 형식이 아닙니다. 다시 입력해주세요."
                findPasswordBinding.userEmail.text.clear()
            }
            else -> findPasswordViewModel.sendPasswordResetEmail(userEmail)
        }
    }

}