package com.example.projecteyebrow.view.detailedItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDescribeSection(modifier: Modifier) {
    Column(
        modifier = modifier.padding(10.dp),
        content = {
            Card(
                modifier = modifier.fillMaxWidth().height(300.dp),
                shape = ShapeDefaults.Small,
                content = { /* TODO("Image")*/ }
            )
            ItemPriceDescribeSection(
                modifier = Modifier,
                tattooistName = "타투이스트 홍길동",
                shareButton = { /* TODO */ }
            )
            Text(
                text = "타투 제목",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "00만원")
            Spacer(modifier = modifier.size(10.dp))
            HorizontalDivider()
            Spacer(modifier = modifier.size(10.dp))
            TattooistOtherItemView(
                modifier = modifier,
                tattooistName = "타투이스트 홍길동"
            )
        }
    )
}

@Composable
fun ItemPriceDescribeSection(
    modifier: Modifier,
    tattooistName: String,
    shareButton: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                text = tattooistName,
                modifier = modifier.clickable {  },
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = shareButton,
                content = { Icon(imageVector = Icons.Outlined.Share, contentDescription = "") }
            )
        }
    )
}

@Composable
fun TattooistOtherItemView(
    modifier: Modifier,
    tattooistName: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        content = {
            Text(
                text = tattooistName+"님 작품",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = modifier.size(5.dp))
        }
    )
}

@Preview(group = "item", showBackground = true)
@Composable
fun PreviewTattooistItems() {
    MaterialTheme {
        TattooistOtherItemView(
            modifier = Modifier,
            tattooistName = "타투이스트 홍길동"
        )
    }
}

@Preview(group = "item", showBackground = true)
@Composable
fun PreviewItemPriceDescribe() {
    MaterialTheme {
        ItemPriceDescribeSection(
            modifier = Modifier,
            tattooistName = "타투이스트 홍길동",
            shareButton = { /* TODO */ }
        )
    }
}

@Preview(group = "view", showBackground = true, showSystemUi = true)
@Composable
fun PreviewItemDescribe() {
    MaterialTheme { ItemDescribeSection(modifier = Modifier) }
}