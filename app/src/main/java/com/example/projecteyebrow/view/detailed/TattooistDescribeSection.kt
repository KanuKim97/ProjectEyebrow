package com.example.projecteyebrow.view.detailed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projecteyebrow.ui.theme.Shape

@Composable
fun TattooistDescSection(
    modifier: Modifier,
    onNavigateTattooistBtnClick: () -> Unit,
    onTalkBtnClick: () -> Unit,
    addCollectionBtnClick: () -> Unit,
    shareBtnClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(10.dp),
        content = {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = Shape.large,
                content = { /* TODO("Image) */ }
            )
            TattooistNameDescRow(
                modifier = modifier,
                tattooistName = "타투이스트 홍길동",
                onNavigateTattooistInfoBtnClick = onNavigateTattooistBtnClick,
                onTalkBtnClick = onTalkBtnClick,
                addCollectionBtnClick = addCollectionBtnClick,
                shareBtnClick = shareBtnClick
            )
            Spacer(modifier = modifier.size(10.dp))
            HorizontalDivider()
            Spacer(modifier = modifier.size(10.dp))
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewDescSection() {
    TattooistDescSection(
        modifier = Modifier,
        onNavigateTattooistBtnClick = {  },
        onTalkBtnClick = {  },
        addCollectionBtnClick = {  },
        shareBtnClick = {  }
    )
}