package com.example.projecteyebrow.view.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mode
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.projecteyebrow.WriteContent
import com.example.projecteyebrow.ui.theme.Shape

@Composable
fun CommunityContentPage(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            WriteContentFabBtn(onClickBtn = { navController.navigate(WriteContent.route) })
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { contentPadding ->
            Column(
                modifier = Modifier.padding(contentPadding),
                content = { CommunityContentList() }
            )
        }
    )
}

@Composable
fun WriteContentFabBtn(
    onClickBtn: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClickBtn,
        modifier = modifier.wrapContentSize()
            .clip(Shape.extraLarge)
            .background(Color.Cyan),
        content = {
            Icon(
                imageVector = Icons.Outlined.Mode,
                contentDescription = "toWriteContentPage",
                tint = Color.White
            )
        }
    )
}