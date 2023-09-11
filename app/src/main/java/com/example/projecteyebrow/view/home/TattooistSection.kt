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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projecteyebrow.R
import com.example.projecteyebrow.TattooistDetailed
import com.example.projecteyebrow.view.util.TattooistItem

@Composable
fun TattooistSection(
    navController: NavController,
    tattooistItemList: ArrayList<TattooistItem>
) {
    Surface(
        modifier = Modifier.padding(start = 10.dp, top = 20.dp),
        content = {
            Column {
                TattooistTitle()
                TattooistSubTitle()
                TattooistViewList(
                    modifier = Modifier,
                    navController = navController,
                    itemList = tattooistItemList
                )
            }
        }
    )
}

@Composable
fun TattooistTitle() {
    Text(
        text = stringResource(id = R.string.tattooist),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TattooistSubTitle() {
    Text(
        text = stringResource(id = R.string.tattooist_SubTitle),
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun TattooistViewList(
    modifier: Modifier,
    navController: NavController,
    itemList: List<TattooistItem>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        content = {
            items(itemList.size) { tattooistItem ->
                TattooistListItem(
                    modifier = modifier,
                    navController = navController,
                    tattooistItem = itemList[tattooistItem]
                )
                Spacer(modifier = modifier.size(10.dp))
            }
        }
    )
}

@Composable
fun TattooistListItem(
    modifier: Modifier,
    navController: NavController,
    tattooistItem: TattooistItem
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .aspectRatio(1f)
            .clickable { navController.navigate(TattooistDetailed.route) },
        contentAlignment = Alignment.Center,
        content = {
            Column {
                Image(
                    modifier = modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = stringResource(id = R.string.imageview)
                )
                Text(
                    text = tattooistItem.tattooistName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = tattooistItem.tattooistRating,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    )
}