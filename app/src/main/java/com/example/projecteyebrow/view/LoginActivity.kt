package com.example.projecteyebrow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projecteyebrow.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
    }

    override fun onResume() {
        super.onResume()

        loginBinding.LogInBtn.setOnClickListener(this)
        loginBinding.signInBtn.setOnClickListener(this)
        loginBinding.findPasswordBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }
}