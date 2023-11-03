package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun LogInSection() {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = "이메일", style = Typography.labelLarge) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = "비밀번호", style = Typography.labelLarge) },
            trailingIcon = {
                val iconImage: ImageVector = if (passwordVisible) {
                    Icons.Outlined.Visibility
                } else {
                    Icons.Outlined.VisibilityOff
                }
                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    content = {
                        Icon(
                            imageVector = iconImage,
                            contentDescription = "password Toggle",
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                )
            },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
        Spacer(modifier = Modifier.height(100.dp))
        LogInBtn(modifier = Modifier, toLogInBtnClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(30.dp))
        LogInRow(modifier = Modifier, onClickSignIn = { /* TODO */ }, onClickFindPWD = { /* TODO */ })
    }
}