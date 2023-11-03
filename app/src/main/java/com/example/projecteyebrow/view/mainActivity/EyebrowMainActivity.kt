package com.example.projecteyebrow.view.mainActivity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.projecteyebrow.EyebrowNavHost

@Composable
fun EyeBrowMainActivity(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { ActivityBottomNavigationBar(navController = navController) }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            content = { EyebrowNavHost(navHostController = navController) }
        )
    }
}