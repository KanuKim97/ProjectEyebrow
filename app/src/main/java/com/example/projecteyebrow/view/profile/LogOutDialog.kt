package com.example.projecteyebrow.view.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LogOutDialog(
    modifier: Modifier = Modifier,
    onDismissRequestOnDialog: () -> Unit,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequestOnDialog,
        modifier = modifier,
        icon = { Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = "") },
        title = { Text(text = "로그아웃 하시겠습니까?") },
        confirmButton = {
            Button(
                onClick = onConfirmClick,
                content = { Text(text = "네") }
            )
        },
        dismissButton = {
            Button(
                onClick = onDismissClick,
                content = { Text(text = "아니요") }
            )
        },
        shape = ShapeDefaults.Large,
        tonalElevation = 8.dp
    )
}