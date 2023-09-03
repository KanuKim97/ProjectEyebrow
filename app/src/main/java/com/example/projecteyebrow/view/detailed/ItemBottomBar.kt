package com.example.projecteyebrow.view.detailed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DetailedItemBottomBar(
    modifier: Modifier,
    addBookMarkBtn: () -> Unit,
    contactTattooistBtn: () -> Unit
) {
    BottomAppBar (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        content = {
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    IconButton(
                        onClick = addBookMarkBtn,
                        modifier = modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        content = { Icon(imageVector = Icons.Outlined.BookmarkAdd, contentDescription = "") }
                    )
                    Button(
                        onClick = contactTattooistBtn,
                        modifier = modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                            .clip(shape = ShapeDefaults.ExtraSmall),
                        content = {
                            Text(
                                text = "문의하기",
                                fontWeight = FontWeight.Medium,
                                fontSize = 15.sp
                            )
                        }
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomBar() {
    MaterialTheme { DetailedItemBottomBar(Modifier, {}, {}) }
}