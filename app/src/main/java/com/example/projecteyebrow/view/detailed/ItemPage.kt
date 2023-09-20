package com.example.projecteyebrow.view.detailed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailedItemPage() {
    Scaffold(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            content = { ItemDescSection(modifier = Modifier) }
        )
    }
}

@Preview
@Composable
fun PreviewDetailedItem() {
    DetailedItemPage()
}