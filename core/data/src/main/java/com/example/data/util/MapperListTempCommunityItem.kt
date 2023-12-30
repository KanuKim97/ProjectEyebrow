package com.example.data.util

import com.example.data.localDataBase.table.TempContentTable
import com.example.domain.model.TempContentModel

fun mappingToListTempCommunityItem(
    tempTableList: List<TempContentTable>
): List<TempContentModel> = tempTableList.map {
    TempContentModel(
        it.itemID,
        it.title,
        it.content,
        it.imageUriList,
        it.timeStamp
    )
}