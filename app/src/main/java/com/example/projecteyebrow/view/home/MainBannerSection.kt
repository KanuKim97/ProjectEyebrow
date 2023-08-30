package com.example.projecteyebrow.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainBannerSection(
    count: Int,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { count })

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color.DarkGray,
            content = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = { Text("Page $it", textAlign = TextAlign.Center, fontSize = 40.sp) }
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMainBanner() {
    MainBannerSection(count = 5)
}