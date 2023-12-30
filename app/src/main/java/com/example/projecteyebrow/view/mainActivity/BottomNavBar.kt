package com.example.projecteyebrow.view.mainActivity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecteyebrow.Collection
import com.example.projecteyebrow.Community
import com.example.projecteyebrow.Home
import com.example.projecteyebrow.Profile
import com.example.projecteyebrow.ui.preview.ComponentPreview

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.navigate(Home.route) },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Icon Home"
                    )
                }
            )
            IconButton(
                onClick = { navController.navigate(Collection.route) },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Collections,
                        contentDescription = "Icon Collection"
                    )
                }
            )
            IconButton(
                onClick = { navController.navigate(Community.route) },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.ModeComment,
                        contentDescription = "Icon Community"
                    )
                }
            )
            IconButton(
                onClick = { navController.navigate(Profile.route) },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.People,
                        contentDescription = "Icon Profile & LogIn"
                    )
                }
            )
        }
    }
}

@ComponentPreview
@Composable
fun PreviewBottomNAvBar() {
    BottomNavBar(rememberNavController())
}
