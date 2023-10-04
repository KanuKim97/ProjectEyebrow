package com.example.data.util

import com.example.data.localDataBase.table.TempContentTable
import com.example.domain.model.TemporaryCommunityItem

fun mappingToListTempCommunityItem(
    tempTableList: List<TempContentTable>
): List<TemporaryCommunityItem> = tempTableList.map {
    TemporaryCommunityItem(
        it.itemID,
        it.title,
        it.content,
        it.imageUriList,
        it.timeStamp
    )
}