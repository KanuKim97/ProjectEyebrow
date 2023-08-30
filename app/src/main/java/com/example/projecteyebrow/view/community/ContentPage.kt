package com.example.projecteyebrow.view.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContentPage(
    onClickBtn: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { WriteContentFabBtn(modifier = Modifier, onClickBtn = onClickBtn)},
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
            content = { CommunityContentList() }
        )
    }
}

@Composable
fun WriteContentFabBtn(
    modifier: Modifier,
    onClickBtn: () -> Unit
) {
    IconButton(
        onClick = onClickBtn,
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        content = { Icon(imageVector = Icons.Outlined.Add, contentDescription = "") }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewContentPage() {
    ContentPage {}
}