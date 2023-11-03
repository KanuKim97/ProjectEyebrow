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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecteyebrow.FindPWD
import com.example.projecteyebrow.SignIn

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
                        UserLogInSection(
                            navController = navController,
                            toFindPWDBtnClick = { navController.navigate(FindPWD.route) },
                            toSignInAccountBtnClick = { navController.navigate(SignIn.route) }
                        )
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun Preview() {
    LogInPage(navController = rememberNavController())
}