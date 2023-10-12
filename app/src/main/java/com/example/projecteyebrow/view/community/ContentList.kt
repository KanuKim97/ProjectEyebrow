package com.example.projecteyebrow.view.community

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projecteyebrow.viewModel.CommunityViewModel

@Composable
fun CommunityContentList(
    modifier: Modifier = Modifier,
    communityViewModel: CommunityViewModel = hiltViewModel()
) {
    val itemList by communityViewModel.communityList.collectAsState(initial = arrayListOf())

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        content = {
            items(itemList.size) { communityItem ->
                CommunityContentItem(communityItem = itemList[communityItem])
                Spacer(modifier = modifier.size(5.dp))
            }
        }
    )
}

