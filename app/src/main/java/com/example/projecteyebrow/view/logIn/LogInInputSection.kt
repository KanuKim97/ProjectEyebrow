package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.R

@Composable
fun LogInUserInputSection() {
    var userEmailInput by remember { mutableStateOf("") }
    var userPasswordInput by remember { mutableStateOf("") }
    
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = userEmailInput,
            onValueChange = { userEmailInput = it },
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = stringResource(id = R.string.userEmail_Hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            value = userPasswordInput,
            onValueChange = { userPasswordInput = it },
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = stringResource(id = R.string.userPassword_Hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
    }
}

@Preview(name = "InputSection", group = "Item", showBackground = true)
@Composable
fun PreviewInputSection() {
    LogInUserInputSection()
}