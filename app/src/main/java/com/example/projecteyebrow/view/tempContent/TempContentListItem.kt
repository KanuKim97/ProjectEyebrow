package com.example.projecteyebrow.view.tempContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.TempContentModel
import com.example.projecteyebrow.ui.theme.Shape
import com.example.projecteyebrow.ui.theme.Typography

@Composable
fun TempContentListItem(
    modifier: Modifier = Modifier,
    tempContent: TempContentModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        shape = Shape.large,
        elevation = CardDefaults.cardElevation(10.dp),
        content = {
            Column(
                modifier = modifier.padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                content = {
                    Text(
                        text = tempContent.tempTitle.toString(),
                        modifier = modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = Typography.titleLarge
                    )
                    Text(
                        text = tempContent.tempContent.toString(),
                        modifier = modifier.fillMaxWidth().wrapContentHeight(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3,
                        style = Typography.bodyMedium
                    )
                }
            )
        }
    )
}