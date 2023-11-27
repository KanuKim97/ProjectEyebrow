package com.example.projecteyebrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projecteyebrow.view.mainActivity.EyeBrowMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EyebrowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            EyeBrowMainActivity(navController = navController)
        }
    }
}