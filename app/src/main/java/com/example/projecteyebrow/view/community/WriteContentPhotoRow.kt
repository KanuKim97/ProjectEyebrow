package com.example.projecteyebrow.view.community

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UploadPhotoRow(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Box(
                modifier = modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(Color.LightGray)
                    .clip(ShapeDefaults.Medium)
                    .clickable { /* TODO */ }
            )
            Box(
                modifier = modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(Color.LightGray)
                    .clip(ShapeDefaults.Medium)
                    .clickable { /* TODO */ }
            )
            Box(
                modifier = modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(Color.LightGray)
                    .clip(ShapeDefaults.Medium)
                    .clickable { /* TODO */ }
            )
        }
    )
}