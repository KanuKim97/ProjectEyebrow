package com.example.projecteyebrow.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
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
    Surface(
        modifier = modifier.fillMaxSize(),
        content = {
            Column {
                MainBannerSection(count = 5)
                Spacer(modifier = Modifier.size(20.dp))
                BrandNewSection(navController = navController, brandNewItemList = brandNewList)
                HotViewSection(navController = navController, hotViewItemList = hotViewList)
                TattooistSection(navController = navController, tattooistItemList = tattooistList)
            }
        }
    )
}