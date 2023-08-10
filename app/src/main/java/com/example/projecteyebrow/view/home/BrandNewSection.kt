package com.example.projecteyebrow.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecteyebrow.R
import com.example.projecteyebrow.view.adapter.adapterItems.BrandNewItem



@Composable
fun BrandNewSection(brandNewItemList: ArrayList<BrandNewItem>) {
    Surface(modifier = Modifier.padding(start = 10.dp, top = 20.dp)) {
        Column {
            BrandNewTitle()
            BrandNewSubTitle()
            BrandNewList(modifier = Modifier, itemList = brandNewItemList)
        }
    }
}

@Composable
fun BrandNewTitle() {
    Text(
        text = stringResource(id = R.string.brandNew),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BrandNewSubTitle() {
    Text(
        text = stringResource(id = R.string.brandNew_Kor_Txt),
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun BrandNewList(
    modifier: Modifier,
    itemList: List<BrandNewItem>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        content = {
            items(itemList.size) { newItem ->
                BrandNewListItem(modifier = modifier, brandNewItem = itemList[newItem])
                Spacer(modifier = modifier.size(15.dp))
            }
        }
    )
}

@Composable
fun BrandNewListItem(
    modifier: Modifier,
    brandNewItem: BrandNewItem
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .aspectRatio(1f)
            .clickable { /* TODO */ },
        contentAlignment = Alignment.Center,
        content = {
            Column {
                Image(
                    modifier = modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = stringResource(id = R.string.imageview)
                )
                Text(
                    text = brandNewItem.brandNewTitle,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = brandNewItem.brandNewSubTitle,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    )
}