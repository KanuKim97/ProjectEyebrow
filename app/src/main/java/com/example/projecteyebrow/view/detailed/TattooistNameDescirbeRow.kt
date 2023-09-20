package com.example.projecteyebrow.view.detailed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TattooistNameDescRow(
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