package com.example.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.Shape

@Composable
fun EyeBrowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = Shape.extraLarge,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun EyeBrowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = Shape.extraLarge,
    leadingIcon: @Composable (() -> Unit)? = null,
    text: @Composable () -> Unit
) {
    EyeBrowButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        }
    ) {
        ButtonContent(
            text = text,
            leadingIcon = leadingIcon
        )
    }
}

@Composable
fun EyeBrowOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = Shape.extraLarge,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            }
        ),
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun EyeBrowOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = Shape.extraLarge,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    EyeBrowOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        }
    ) {
        ButtonContent(
            text = text,
            leadingIcon = leadingIcon
        )
    }
}


@Composable
private fun ButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    if (leadingIcon != null) {
        Box(
            modifier = Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize),
            content = { leadingIcon() }
        )
    }
    Box(
        modifier = Modifier.padding(
            start = if (leadingIcon != null) {
                ButtonDefaults.IconSpacing
            } else {
                0.dp
            }
        ),
        content = { text() }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEyeBrowOutlinedBtn() {
    EyeBrowOutlinedButton(
        onClick = { /*TODO*/ },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LaptopChromebook,
                contentDescription = ""
            )
        },
        text = { Text(text = "로그인 화면으로 이동하기") }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEyeBrowBtn() {
    EyeBrowButton(
        onClick = { /*TODO*/ },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LaptopChromebook,
                contentDescription = ""
            )
        },
        text = { Text(text = "로그인 화면으로 이동하기") }
    )
}