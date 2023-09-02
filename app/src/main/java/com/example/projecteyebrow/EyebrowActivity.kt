package com.example.projecteyebrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projecteyebrow.view.activity.EyeBrowMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EyebrowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { EyeBrowMainActivity() }
    }
}