package com.example.projecteyebrow.view.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.CommunityItem
import com.example.projecteyebrow.viewModel.CommunityViewModel

@Composable
fun CommunityContentList(communityViewModel: CommunityViewModel = hiltViewModel()) {
    val itemList = communityViewModel.communityList.collectAsState(initial = arrayListOf()).value

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(itemList.size) { communityItem ->
                CommunityContentItem(modifier = Modifier, communityItem = itemList[communityItem])
                Spacer(modifier = Modifier.size(5.dp))
            }
        }
    )
}

@Composable
fun CommunityContentItem(
    modifier: Modifier,
    communityItem: CommunityItem
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { /*TODO*/ },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            content = {
                Text(
                    text = communityItem.title.toString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = communityItem.content.toString(),
                    modifier = modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        )
    }
}