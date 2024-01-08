package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecteyebrow.FindPWD
import com.example.projecteyebrow.SignIn
import com.example.ui.DevicePreview

@Composable
fun LogInPage(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    content = { LogInTitleSection() }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        LogInSection(
                            navController = navController,
                            toSignInBtnClick = { navController.navigate(SignIn.route) },
                            toFindPWDBtnClick = { navController.navigate(FindPWD.route) }
                        )
                    }
                )
            }
        }
    )
}

@DevicePreview
@Composable
fun PreviewLogInPage() {
    LogInPage(navController = rememberNavController())
}