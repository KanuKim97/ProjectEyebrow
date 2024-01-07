package com.example.data.util

import com.example.database.model.TempContentModel
import com.example.model.TempContent

fun mappingToTempCommunityTable(content: TempContent): TempContentModel =
    TempContentModel(
        itemID = content.tempID,
        title = content.tempTitle,
        content = content.tempContent,
        imageUriList = listOf(),
        timeStamp = content.timeStamp
    )