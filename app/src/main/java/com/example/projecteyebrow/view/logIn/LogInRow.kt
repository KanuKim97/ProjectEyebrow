package com.example.projecteyebrow.view.logIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.R
import com.example.projecteyebrow.ui.preview.ComponentPreview
import com.example.projecteyebrow.ui.theme.Typography

@Composable
fun LogInRow(
    modifier: Modifier,
    onClickSignIn: () -> Unit,
    onClickFindPWD: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.CreateAccount_Btn),
            modifier = modifier
                .wrapContentHeight()
                .clickable(onClick = onClickSignIn),
            style = Typography.labelLarge
        )
        VerticalDivider(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.FindPassword_Btn),
            modifier = modifier
                .wrapContentHeight()
                .clickable(onClick = onClickFindPWD),
            style = Typography.labelLarge
        )
    }
}

@ComponentPreview
@Composable
fun PreviewLogInRow() {
    LogInRow(modifier = Modifier, onClickSignIn = { /*TODO*/ }) {

    }
}