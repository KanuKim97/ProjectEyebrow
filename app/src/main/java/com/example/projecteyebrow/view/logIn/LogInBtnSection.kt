package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.R

@Composable
fun LogInBtnSection(
    toFindPasswordBtnClick: () -> Unit,
    userLogInBtnClick: () -> Unit,
    toCreateAccountBtnClick: () -> Unit
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            content = {
                FindPasswordBtn(modifier = Modifier, toFindPasswordBtnClick = toFindPasswordBtnClick)
                LogInBtn(modifier = Modifier, userLogInBtnClick = userLogInBtnClick)
                CreateUserAccountBtn(modifier = Modifier, toCreateAccountBtnClick = toCreateAccountBtnClick)
            }
        )
    }
}


@Composable
fun FindPasswordBtn(
    modifier: Modifier,
    toFindPasswordBtnClick: () -> Unit
) {
    Button(
        onClick = toFindPasswordBtnClick,
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
    userLogInBtnClick: () -> Unit
) {
    Button(
        onClick = userLogInBtnClick,
        modifier = modifier
            .width(320.dp)
            .background(Color.White),
        shape = ShapeDefaults.Medium,
        content = { Text(text = stringResource(id = R.string.Login_Btn)) }
    )
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