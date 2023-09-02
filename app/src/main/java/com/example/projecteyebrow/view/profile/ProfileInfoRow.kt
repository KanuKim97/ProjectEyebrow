package com.example.projecteyebrow.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.projecteyebrow.R

@Composable
fun ProfileInfoRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Image(painter = painterResource(id = R.drawable.ic_blank_profile), contentDescription = "")
    }
}

@Composable
@Preview
fun PreviewProfileInfoRow() {
    ProfileInfoRow()
}