package com.example.data.util

import com.example.data.localDataBase.table.TemporaryItemTable
import com.example.domain.model.TemporaryCommunityItem

fun mappingToListTempCommunityItem(
    tempTableList: List<TemporaryItemTable>
): List<TemporaryCommunityItem> = tempTableList.map {
    TemporaryCommunityItem(
        it.contentID,
        it.contentTitle,
        it.TemporaryContent,
        it.TemporaryContentImage,
        it.contentTime
    )
}