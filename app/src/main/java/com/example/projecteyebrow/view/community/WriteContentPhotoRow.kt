package com.example.projecteyebrow.view.community

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.ui.theme.Shape
import com.example.projecteyebrow.ui.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UploadPhotoRow(
    modifier: Modifier,
    imageUriList: List<Uri>,
    onClickOpenPhotoPicker: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClickOpenPhotoPicker),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            if (imageUriList.isEmpty()) {
                Button(
                    onClick = onClickOpenPhotoPicker,
                    shape = Shape.large,
                    content = {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            content = {
                                Icon(imageVector = Icons.Outlined.Image, contentDescription = "")
                                Spacer(modifier = modifier.size(5.dp))
                                Text(text = "사진 추가하기", style = Typography.labelLarge)
                            }
                        )
                    }
                )
            } else {
                LazyRow {
                    items(imageUriList) {
                        GlideImage(
                            imageModel = { it },
                            modifier = modifier
                                .width(100.dp)
                                .fillMaxHeight()
                                .padding(5.dp),
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            )
                        )
                    }
                }
            }
        }
    )
}