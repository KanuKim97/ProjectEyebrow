package com.example.projecteyebrow.view.findPWD

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projecteyebrow.R
import com.example.projecteyebrow.LogInPage
import com.example.projecteyebrow.view.util.States
import com.example.projecteyebrow.viewModel.FindPWDViewModel

@Composable
fun FindPWDInputSection(
    navController: NavController,
    findPWDViewModel: FindPWDViewModel = hiltViewModel()
) {
    var userEmail by remember { mutableStateOf("") }

    val resetEmailState by findPWDViewModel.resetEmailState.collectAsState()
    val localContext: Context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = stringResource(id = R.string.userEmail_Hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
        Spacer(modifier = Modifier.height(100.dp))

        when (resetEmailState) {
            is States.Idle -> {
                Button(
                    onClick = { findPWDViewModel.sendResetPassword(userEmail) },
                    modifier = Modifier.width(320.dp),
                    shape = ShapeDefaults.Medium,
                    content = { Text(text = stringResource(id = R.string.FindPassword_Btn)) }
                )
            }
            is States.IsLoading -> { CircularProgressIndicator() }
            is States.IsSuccess -> {
                Toast.makeText(
                    localContext,
                    "비밀번호 초기화 메일을 전송했습니다.",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate(LogInPage.route)
            }
            is States.IsFailed -> {
                Toast.makeText(
                    localContext,
                    "비밀번호 초기화 메일을 전송에 실패했습니다.\n메일 주소를 다시 확인해 주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}