package com.example.projecteyebrow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.ActivityFindPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var findPasswordBinding: ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPasswordBinding = ActivityFindPasswordBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_find_password)
    }

    override fun onResume() {
        super.onResume()


        findPasswordBinding.resetEmailBtn.setOnClickListener(this)
        findPasswordBinding.toLoginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

}