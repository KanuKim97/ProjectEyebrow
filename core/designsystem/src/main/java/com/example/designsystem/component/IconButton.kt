package com.example.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EyeBrowIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewIconBtn() {
    EyeBrowIconButton(
        onClick = { /*TODO*/ },
        content = { Icon(imageVector = Icons.Default.BorderColor, contentDescription = "") }
    )
}