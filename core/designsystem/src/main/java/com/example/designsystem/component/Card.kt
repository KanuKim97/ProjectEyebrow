package com.example.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.example.designsystem.theme.Shape

@Composable
fun EyeBrowCard(
    modifier: Modifier = Modifier,
    shape: Shape = Shape.extraLarge,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        content = content
    )
}

@Composable
fun EyeBrowClickableCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    shape: Shape = Shape.extraLarge,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        enabled = enable,
        shape = shape,
        content = content
    )
}