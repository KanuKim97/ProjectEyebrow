package com.example.projecteyebrow.view.findPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecteyebrow.R

@Composable
fun FindPWDTitleSection() {
    Surface(modifier = Modifier.padding(15.dp)) {
        Column {
            FindPasswordTitle()
            FindLostPasswordTitle()
            FindPasswordSubTitle()
        }
    }
}

@Composable
fun FindPasswordTitle() {
    Text(
        text = stringResource(id = R.string.Password_Title),
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun FindLostPasswordTitle() {
    Text(
        text = stringResource(id = R.string.Forgot_Title),
        color = Color.DarkGray,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun FindPasswordSubTitle() {
    Text(
        text = stringResource(id = R.string.FindPassword_SubTitle),
        color = Color.DarkGray,
        fontSize = 13.sp,
        fontWeight = FontWeight.Light
    )
}