package com.example.projecteyebrow.view.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LogOutFloatingBtn(
    modifier: Modifier = Modifier,
    onDialogClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = onDialogClick,
        modifier = modifier,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        content = {
            Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = "")
            Spacer(modifier = modifier.size(5.dp))
            Text(text = "로그아웃 하기")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFloatingBtn() {
    LogOutFloatingBtn(onDialogClick = {  })
}