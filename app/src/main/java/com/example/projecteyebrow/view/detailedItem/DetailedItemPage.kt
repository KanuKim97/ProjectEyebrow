package com.example.projecteyebrow.view.detailedItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailedItemPage() {
    Scaffold(
        modifier = Modifier.padding(15.dp),
        bottomBar = {
            DetailedItemBottomBar(
                modifier = Modifier,
                addBookMarkBtn = { /* TODO */ },
                contactTattooistBtn = { /* TODO */ }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            content = { ItemDescribeSection(modifier = Modifier) }
        )
    }
}

@Preview
@Composable
fun PreviewDetailedItem() {
    DetailedItemPage()
}