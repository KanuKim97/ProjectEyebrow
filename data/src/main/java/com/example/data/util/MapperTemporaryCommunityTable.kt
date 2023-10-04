package com.example.data.util

import com.example.data.localDataBase.table.TempContentTable
import com.example.domain.model.TemporaryCommunityItem

fun mappingToTempCommunityTable(content: TemporaryCommunityItem): TempContentTable =
    TempContentTable(
        itemID = content.tempID,
        title = content.tempTitle,
        content = content.tempContent,
        imageUriList = content.tempImageUriList,
        timeStamp = content.timeStamp
    )