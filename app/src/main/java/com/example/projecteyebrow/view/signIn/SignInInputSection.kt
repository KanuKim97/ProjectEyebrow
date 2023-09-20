package com.example.projecteyebrow.view.signIn

import android.util.Patterns
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projecteyebrow.R
import com.example.projecteyebrow.view.util.States
import com.example.projecteyebrow.viewModel.SignInViewModel

@Composable
fun SignInSection(
    toLogInPageClicked: () -> Unit,
    signUpViewModel: SignInViewModel = hiltViewModel()
) {
    var userEmail by remember { mutableStateOf("") }
    var userNickName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val signInState by signUpViewModel.signInState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
        Spacer(modifier = Modifier.size(5.dp))
        OutlinedTextField(
            value = userNickName,
            onValueChange = { userNickName = it },
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = stringResource(id = R.string.userNickName_Hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
        Spacer(modifier = Modifier.size(5.dp))
        OutlinedTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = stringResource(id = R.string.userPassword_Hint)) },
            trailingIcon = {
                val iconImage: ImageVector = if(passwordVisible) {
                    Icons.Outlined.Visibility
                } else {
                    Icons.Outlined.VisibilityOff
                }

                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    content = { Icon(imageVector = iconImage, contentDescription = "passwordToggle") }
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
        Spacer(modifier = Modifier.size(5.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier
                .width(320.dp)
                .height(60.dp),
            label = { Text(text = stringResource(id = R.string.userPassword_confirm_Hint)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = ShapeDefaults.Medium
        )
        Spacer(modifier = Modifier.size(50.dp))
        when (signInState) {
            is States.Idle -> {
                CreateUserAccountBtn(
                    modifier = Modifier,
                    toCreateAccountBtnClick = {
                        /* TODO("Input Exception Handling")*/
                        when {
                            !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() -> { }
                            userNickName.length >= 15 -> { }
                            userPassword != confirmPassword -> {  }
                            else -> signUpViewModel.createUserAccount(userEmail, userPassword, userNickName)
                        }
                    }
                )
            }
            is States.IsLoading -> CircularProgressIndicator()
            is States.IsSuccess -> {  }
            is States.IsFailed -> {  }
        }


        Spacer(modifier = Modifier.size(5.dp))
        ToLogInPageBtn(modifier = Modifier, toLogInPageBtnClick = toLogInPageClicked)
    }
}

@Composable
fun CreateUserAccountBtn(
    modifier: Modifier,
    toCreateAccountBtnClick: () -> Unit
) {
    Button(
        onClick = toCreateAccountBtnClick,
        modifier = modifier
            .width(320.dp)
            .background(Color.White),
        shape = ShapeDefaults.Medium,
        content = { Text(text = stringResource(id = R.string.CreateAccount_Btn)) }
    )
}

@Composable
fun ToLogInPageBtn(
    modifier: Modifier,
    toLogInPageBtnClick: () -> Unit
) {
    Button(
        onClick = toLogInPageBtnClick,
        modifier = modifier
            .width(320.dp)
            .background(Color.White),
        shape = ShapeDefaults.Medium,
        content = { Text(text = stringResource(id = R.string.toLogInFragment_Btn)) }
    )
}