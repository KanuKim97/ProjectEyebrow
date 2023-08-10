package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecteyebrow.R

@Composable
fun LogInTitleSection() {
    Surface(modifier = Modifier.padding(15.dp)) {
        Column {
            LogInWelcomeTitle()
            LogInAppTitle()
            LogInSubTitle()
        }
    }
}

@Composable
fun LogInWelcomeTitle() {
    Text(
        text = stringResource(id = R.string.welcomeTitle),
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun LogInAppTitle() {
    Text(
        text = "내일 입니다!",
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun LogInSubTitle() {
    Text(
        text = stringResource(id = R.string.LogIn_SubTitle),
        fontSize = 13.sp,
        fontWeight = FontWeight.Light
    )
}