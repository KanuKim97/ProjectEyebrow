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
import com.example.projecteyebrow.ui.theme.Typography

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
        style = Typography.headlineLarge,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun SignInAppTitle() {
    Text(
        text = "환영합니다!",
        color = Color.DarkGray,
        style = Typography.displayMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SignInSubTitle() {
    Text(
        text = "회원가입으로 다양한 컨텐츠들을 사용하세요!",
        color = Color.DarkGray,
        style = Typography.labelMedium
    )
}