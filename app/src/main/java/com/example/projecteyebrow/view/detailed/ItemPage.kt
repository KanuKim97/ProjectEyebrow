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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecteyebrow.TattooistDetailed

@Composable
fun DetailedItemPage(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            content = {
                ItemDescSection(
                    modifier = Modifier,
                    onNavigateTattooistBtnClick = { navController.navigate(TattooistDetailed.route) },
                    onTalkBtnClick = {  },
                    addCollectionBtnClick = {  },
                    shareBtnClick = {  }
                )
            }
        )
    }
}

@Preview
@Composable
fun PreviewDetailedItem() {
    DetailedItemPage(navController = rememberNavController())
}