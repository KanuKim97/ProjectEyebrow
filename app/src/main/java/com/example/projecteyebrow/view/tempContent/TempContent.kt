package com.example.projecteyebrow.view.tempContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.TemporaryCommunityItem
import com.example.projecteyebrow.viewModel.TemporaryContentViewModel

@Composable
fun TempContentListSection(tempContentViewModel: TemporaryContentViewModel = hiltViewModel()) {
    tempContentViewModel.loadAllTempContent()

    val itemList = tempContentViewModel.tempContent.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        content = {
            itemList?.let {
                items(it.size) { tempItem ->
                    TempContentListItem(modifier = Modifier, tempContent = itemList[tempItem])
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
        }
    )
}

@Composable
fun TempContentListItem(
    modifier: Modifier,
    tempContent: TemporaryCommunityItem
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clickable { },
        shape = ShapeDefaults.Small,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            content = {
                Text(
                    text = tempContent.title.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = tempContent.content.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        )
    }
}