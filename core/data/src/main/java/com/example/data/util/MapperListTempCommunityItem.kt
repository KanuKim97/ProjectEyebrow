package com.example.data.util

import com.example.database.model.TempContentModel
import com.example.model.TempContent

fun mappingToListTempCommunityItem(
    tempTableList: List<TempContentModel>
): List<TempContent> = tempTableList.map {
    TempContent(
        it.itemID,
        it.title,
        it.content,
        listOf(),
        it.timeStamp
    )
}