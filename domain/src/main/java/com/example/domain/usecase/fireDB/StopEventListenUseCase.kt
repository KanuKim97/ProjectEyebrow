package com.example.domain.usecase.fireDB

import com.example.domain.repository.FireDBRepository
import javax.inject.Inject

class StopEventListenUseCase @Inject constructor(
    private val fireDB: FireDBRepository
) {
    operator fun invoke(): Unit = fireDB.stopEventListener()
}