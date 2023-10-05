package com.example.projecteyebrow.view.tempContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.TempContentModel
import com.example.projecteyebrow.ui.theme.Shape

@Composable
fun TempContentListItem(
    modifier: Modifier = Modifier,
    tempContent: TempContentModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clickable { },
        shape = Shape.large,
        elevation = CardDefaults.cardElevation(10.dp),
        content = {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                content = {
                    Text(
                        text = tempContent.tempTitle.toString(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = tempContent.tempContent.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        }
    )
}