package com.example.projecteyebrow.view.util.state

sealed class LogInState {
    object Idle: LogInState()
    object IsLoading: LogInState()
    data class IsSuccess(val result: Result<Unit>): LogInState()
    data class IsFailed(val result: Result<Unit>): LogInState()
}
