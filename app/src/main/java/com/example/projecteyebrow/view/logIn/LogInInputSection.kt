package com.example.projecteyebrow.view.logIn

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projecteyebrow.R
import com.example.projecteyebrow.view.util.States
import com.example.projecteyebrow.viewModel.LogInViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator

@Composable
fun UserLogInSection(
    toFindPWDBtnClick: () -> Unit,
    toSignInAccountBtnClick: () -> Unit,
    logInViewModel: LogInViewModel = hiltViewModel()
) {
    var userEmailInput by remember { mutableStateOf("") }
    var userPasswordInput by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val localContext: Context = LocalContext.current
    val userLogInSate by logInViewModel.logInState.collectAsState()

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
            trailingIcon = {
                val iconImage: ImageVector = if (passwordVisible) {
                    Icons.Outlined.Visibility
                } else {
                    Icons.Outlined.VisibilityOff
                }

                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    content = { Icon(imageVector = iconImage, contentDescription = "password Toggle") }
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
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            content = {
                FindPWDBtn(
                    modifier = Modifier,
                    toFindPWDBtnClick = toFindPWDBtnClick
                )
                Spacer(modifier = Modifier.size(5.dp))
                when (userLogInSate) {
                    is States.Idle -> LogInBtn(
                        modifier = Modifier,
                        toLogInBtnClick = {
                            logInViewModel.logInUserAccount(userEmailInput, userPasswordInput)
                        }
                    )
                    is States.IsLoading -> CircularProgressIndicator(localContext)
                    is States.IsSuccess -> {  }
                    is States.IsFailed -> {
                        Toast.makeText(
                            localContext,
                            "로그인에 실패했습니다.",
                            Toast.LENGTH_LONG
                        ).show()

                        LogInBtn(
                            modifier = Modifier,
                            toLogInBtnClick = {
                                logInViewModel.logInUserAccount(userEmailInput, userPasswordInput)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.size(5.dp))
                SignInAccountBtn(
                    modifier = Modifier,
                    toSignInAccountBtnClick = toSignInAccountBtnClick
                )
            }
        )
    }
}

@Composable
fun FindPWDBtn(
    modifier: Modifier,
    toFindPWDBtnClick: () -> Unit
) {
    Button(
        onClick = toFindPWDBtnClick,
        modifier = modifier
            .width(200.dp)
            .background(Color.White),
        shape = ShapeDefaults.Medium,
        content = { Text(text = stringResource(id = R.string.FindPassword_Btn)) }
    )
}

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
        content = { Text(text = stringResource(id = R.string.Login_Btn)) }
    )
}

@Composable
fun SignInAccountBtn(
    modifier: Modifier,
    toSignInAccountBtnClick: () -> Unit
) {
    Button(
        onClick = toSignInAccountBtnClick,
        modifier = modifier
            .width(320.dp)
            .background(Color.White),
        shape = ShapeDefaults.Medium,
        content = { Text(text = stringResource(id = R.string.CreateAccount_Btn)) }
    )
}