package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.R

@Composable
fun LogInBtn(
    modifier: Modifier,
    toLogInBtnClick: () -> Unit
) {
    Button(
        onClick = toLogInBtnClick,
        modifier = modifier
            .width(320.dp)
            .background(Color.White),
        shape = ShapeDefaults.Medium,
        content = { Text(text = stringResource(id = R.string.Login_Btn), style = Typography.labelLarge) }
    )
}