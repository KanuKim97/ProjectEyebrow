package com.example.projecteyebrow.view.detailed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TattooistNameDescRow(
    modifier: Modifier,
    tattooistName: String,
    onNavigateTattooistInfoBtnClick: () -> Unit,
    onTalkBtnClick: () -> Unit,
    addCollectionBtnClick: () -> Unit,
    shareBtnClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                text = tattooistName,
                modifier = modifier.clickable(onClick = onNavigateTattooistInfoBtnClick),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            IconsBtnRow(
                onTalkBtnClick = onTalkBtnClick,
                addCollectionBtnClick = addCollectionBtnClick,
                shareBtnClick = shareBtnClick
            )
        }
    )
}

@Composable
fun IconsBtnRow(
    onTalkBtnClick: () -> Unit,
    addCollectionBtnClick: () -> Unit,
    shareBtnClick: () -> Unit
) {
    Row(
        modifier = Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        content = {
            IconButton(
                onClick = onTalkBtnClick,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Message,
                        contentDescription = "1:1 Talk Btn"
                    )
                }
            )
            IconButton(
                onClick = addCollectionBtnClick,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkAdd,
                        contentDescription = "AddBookMark Btn"
                    )
                }
            )
            IconButton(
                onClick = shareBtnClick,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "Share Btn"
                    )
                }
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewRow() {
    MaterialTheme {
        TattooistNameDescRow(
            modifier = Modifier,
            tattooistName = "타투이스트 홍길동",
            onNavigateTattooistInfoBtnClick = {  },
            onTalkBtnClick = {  },
            addCollectionBtnClick = {  },
            shareBtnClick = {  }
        )
    }
}