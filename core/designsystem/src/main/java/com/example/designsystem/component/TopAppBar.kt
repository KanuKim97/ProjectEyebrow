package com.example.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EyeBrowTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit)
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.surface,
            actionIconContentColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopAppBar() {
    EyeBrowTopAppBar(
        title = {},
        navigationIcon = {
            EyeBrowIconButton(
                onClick = { /*TODO*/ },
                content= {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = ""
                    )
                }
            )
        },
        actions = {
            EyeBrowIconButton(
                onClick = { /*TODO*/ },
                content= {
                    Icon(
                        imageVector = Icons.Default.FileOpen,
                        contentDescription = ""
                    )
                }
            )
            EyeBrowIconButton(
                onClick = { /*TODO*/ },
                content= {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = ""
                    )
                }
            )
            EyeBrowIconButton(
                onClick = { /*TODO*/ },
                content= {
                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = ""
                    )
                }
            )
        }
    )
}