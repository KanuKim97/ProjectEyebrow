package com.example.projecteyebrow.view.util

sealed class States {
    data object Idle: States()
    data object IsLoading: States()
    data class IsSuccess(val result: Result<Unit>): States()
    data class IsFailed(val result: Result<Unit>): States()
}
