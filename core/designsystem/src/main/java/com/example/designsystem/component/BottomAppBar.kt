package com.example.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ModeComment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.rounded.Collections
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ModeComment
import androidx.compose.material.icons.rounded.People
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EyeBrowNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.onSurface,
        contentColor = MaterialTheme.colorScheme.surface,
        content = content
    )
}

@Composable
fun RowScope.EyeBrowNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) { selectedIcon } else { icon },
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemColors(
            selectedIconColor = MaterialTheme.colorScheme.surface,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.surface,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer
        )
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewNavBar() {
    val navIcons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Collections,
        Icons.Outlined.ModeComment,
        Icons.Outlined.People
    )
    val navSelectedIcon = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.Collections,
        Icons.Rounded.ModeComment,
        Icons.Rounded.People
    )
    val navTitle = listOf("홈", "컬렉션", "커뮤니티", "프로필")

    EyeBrowNavigationBar {
        navTitle.forEachIndexed { index, item ->
            EyeBrowNavigationItem(
                selected = index == 0,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = navIcons[index],
                        contentDescription = item
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = navSelectedIcon[index],
                        contentDescription = item
                    )
                },
                label = { Text(text = item) }
            )
        }
    }
}