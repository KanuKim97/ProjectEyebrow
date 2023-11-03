package com.example.projecteyebrow.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projecteyebrow.view.util.BrandNewItem
import com.example.projecteyebrow.view.util.HotViewItem
import com.example.projecteyebrow.view.util.TattooistItem

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    brandNewList: ArrayList<BrandNewItem>,
    hotViewList: ArrayList<HotViewItem>,
    tattooistList: ArrayList<TattooistItem>
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        MainBannerSection(count = 5)
        Spacer(modifier = Modifier.size(20.dp))
        BrandNewSection(navController = navController, brandNewItemList = brandNewList)
        HotViewSection(navController = navController, hotViewItemList = hotViewList)
        TattooistSection(navController = navController, tattooistItemList = tattooistList)
    }
}