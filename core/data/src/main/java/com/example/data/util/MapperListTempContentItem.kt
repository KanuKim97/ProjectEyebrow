package com.example.data.util

import com.example.database.model.TempContentModel
import com.example.model.TempContent

fun mappingToListTempCommunityItem(
    tempTableList: List<TempContentModel>
): List<TempContent> = tempTableList.map { model ->
    TempContent(
        tempID = model.itemID,
        tempTitle = model.title,
        tempContent = model.content,
        tempImgUriList = listOf(),
        timeStamp = model.timeStamp
    )
}