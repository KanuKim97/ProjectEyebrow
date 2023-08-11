package com.example.projecteyebrow.view.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignInTitleSection() {
    Surface(modifier = Modifier.padding(15.dp)) {
        Column {
            SignInWelcomeTitle()
            SignInAppTitle()
            SignInSubTitle()
        }
    }
}

@Composable
fun SignInWelcomeTitle() {
    Text(
        text = "회원가입을",
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun SignInAppTitle() {
    Text(
        text = "환영합니다!",
        color = Color.DarkGray,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SignInSubTitle() {
    Text(
        text = "회원가입으로 다양한 컨텐츠들을 사용하세요!",
        color = Color.DarkGray,
        fontSize = 13.sp,
        fontWeight = FontWeight.Light
    )
}