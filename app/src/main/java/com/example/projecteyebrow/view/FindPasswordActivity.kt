package com.example.projecteyebrow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.ActivityFindPasswordBinding
import com.example.projecteyebrow.viewModel.FindPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var findPasswordBinding: ActivityFindPasswordBinding
    private val findPasswordViewModel: FindPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPasswordBinding = ActivityFindPasswordBinding.inflate(layoutInflater)

        findPasswordBinding.sendResetEmailFindPWBtn.setOnClickListener(this)
        findPasswordBinding.toLoginViewFindPWBtn.setOnClickListener(this)

        setContentView(R.layout.activity_find_password)
    }

    override fun onClick(v: View?) {
        val userEmail: String = getUserEmail()

        when(v?.id) {
            R.id.LogIn_Btn -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.sendResetEmail_FindPW_Btn -> validateUserEmail(userEmail)
        }
    }

    private fun getUserEmail(): String = findPasswordBinding.userEmailFindPWInputField.text.toString()

    private fun validateUserEmail(userEmail: String) {
        when {
            !(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) ->
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            userEmail.isEmpty() ->
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            else -> sendResetPasswordEmail(userEmail)
        }
    }

    private fun sendResetPasswordEmail(userEmail: String) {
        findPasswordViewModel.sendPasswordResetEmail(userEmail)

        findPasswordViewModel.emailResetValue.observe(this) {
            if (it) {
               Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
               startActivity(Intent(this, LoginActivity::class.java))
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                findPasswordBinding.userEmailFindPWInputField.text?.clear()
            }
        }
    }

}