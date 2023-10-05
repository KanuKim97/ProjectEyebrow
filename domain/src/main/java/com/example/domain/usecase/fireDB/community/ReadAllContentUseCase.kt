package com.example.domain.usecase.fireDB.community

import com.example.domain.model.ContentModel
import com.example.domain.repository.FireDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllContentUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    operator fun invoke(): Flow<ArrayList<ContentModel>> = fireDB.readAllContent()
}