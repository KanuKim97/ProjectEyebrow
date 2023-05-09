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
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class FindPasswordActivity : AppCompatActivity(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private val _binding by lazy { ActivityFindPasswordBinding.inflate(layoutInflater) }
    private val findPasswordViewModel: FindPasswordViewModel by viewModels()

    private val userEmail: String by lazy { setUserEmail() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isResetEmailSendSuccess()

        _binding.sendResetEmailFindPWBtn.setOnClickListener(this)
        _binding.toLoginViewFindPWBtn.setOnClickListener(this)

        setContentView(_binding.root)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.LogIn_Btn -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.sendResetEmail_FindPW_Btn -> validateUserEmail(userEmail)
        }
    }

    private fun setUserEmail(): String = _binding.userEmailFindPWInputField.text.toString()

    private fun isEmailPattern(userEmail: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()

    private fun sendPasswordResetEmail(userEmail: String): Job =
        findPasswordViewModel.sendResetEmail(userEmail)

    private fun isResetEmailSendSuccess(): Unit =
        findPasswordViewModel.isSendEmailSuccess.observe(this) { result ->
            if(result.isSuccess) {
                toastMessage.apply {
                    duration = Toast.LENGTH_SHORT
                }.show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                toastMessage.apply {
                    duration = Toast.LENGTH_SHORT
                }.show()
            }
        }

    private fun validateUserEmail(userEmail: String) {
        when {
            !(isEmailPattern(userEmail)) ->
                toastMessage.apply {
                    setText(R.string.IsNotEmailPattern)
                    duration = Toast.LENGTH_SHORT
                }.show()
            userEmail.isEmpty() ->
                toastMessage.apply {
                    setText(R.string.InputUserEmail)
                    duration = Toast.LENGTH_SHORT
                }.show()
            else -> sendPasswordResetEmail(userEmail)
        }
    }

}