package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogInTitleSection() {
    Surface(
        modifier = Modifier.padding(15.dp),
        content = {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                LogInWelcomeTitle()
                LogInAppTitle()
                LogInSubTitle()
            }
        }
    )
}

@Composable
fun LogInWelcomeTitle() {
    Text(
        text = "환영합니다!",
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun LogInAppTitle() {
    Text(
        text = "내일 입니다!",
        color = Color.DarkGray,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun LogInSubTitle() {
    Text(
        text = "로그인하여 다양한 컨텐츠들을 사용하세요!",
        color = Color.DarkGray,
        fontSize = 13.sp,
        fontWeight = FontWeight.Light
    )
}