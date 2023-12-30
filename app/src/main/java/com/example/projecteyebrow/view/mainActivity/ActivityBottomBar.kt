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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.projecteyebrow.Collection
import com.example.projecteyebrow.Community
import com.example.projecteyebrow.Home
import com.example.projecteyebrow.Profile

@Composable
fun ActivityBottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        content = {
            Row(
                modifier = modifier
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
    )
}