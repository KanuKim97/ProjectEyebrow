package com.example.projecteyebrow.view.tempContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projecteyebrow.viewModel.TempContentViewModel

@Composable
fun TempContentListSection(
    modifier: Modifier = Modifier,
    tempContentViewModel: TempContentViewModel = hiltViewModel()
) {
    val tempContents by tempContentViewModel.tempContents.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(5.dp),
        content = {
            items(tempContents) {
                TempContentListItem(tempContent = it)
                Spacer(modifier = Modifier.size(5.dp))
            }
        }
    )
}