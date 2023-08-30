package com.example.projecteyebrow.view.findPassword

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecteyebrow.R

@Composable
fun FindPWDTitleSection() {
    Surface(
        modifier = Modifier.padding(15.dp),
        content = {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                FindPWDTitle()
                FindPWDTitle2()
                FindPWDSubTitle()
            }
        }
    )
}

@Composable
fun FindPWDTitle() {
    Text(
        text = stringResource(id = R.string.Password_Title),
        color = Color.DarkGray,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun FindPWDTitle2() {
    Text(
        text = stringResource(id = R.string.Forgot_Title),
        color = Color.DarkGray,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun FindPWDSubTitle() {
    Text(
        text = stringResource(id = R.string.FindPassword_SubTitle),
        color = Color.DarkGray,
        fontSize = 13.sp,
        fontWeight = FontWeight.Light
    )
}