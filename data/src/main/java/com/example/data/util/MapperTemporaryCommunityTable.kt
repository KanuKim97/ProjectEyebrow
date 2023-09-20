package com.example.data.util

import com.example.data.localDataBase.table.TemporaryItemTable
import com.example.domain.model.TemporaryCommunityItem

fun mappingToTempCommunityTable(content: TemporaryCommunityItem): TemporaryItemTable =
    TemporaryItemTable(
        contentID = content.contentID,
        contentTitle = content.title,
        TemporaryContent = content.content,
        TemporaryContentImage = content.contentImage,
        contentTime = content.contentTimeStamp
    )