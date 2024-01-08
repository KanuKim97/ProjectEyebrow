package com.example.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.designsystem.component.EyeBrowButton
import com.example.designsystem.theme.Typography

@Composable
fun LogInButton(
    modifier: Modifier = Modifier,
    logInUiState: LogInUiState,
    logInSuccess: Unit,
    logInFailed: Unit
) {
    when(logInUiState) {
        is LogInUiState.Idle -> {
            EyeBrowButton(
                onClick = { /*TODO*/ },
                modifier = modifier.fillMaxWidth(),
                leadingIcon = null,
                text = { Text(text = "로그인", style = Typography.labelLarge) }
            )
        }
        is LogInUiState.IsLoading -> { CircularProgressIndicator() }
        is LogInUiState.IsSuccess -> { logInSuccess }
        is LogInUiState.IsFailed -> { logInFailed }
    }
}

sealed interface LogInUiState {
    data object Idle: LogInUiState

    data object IsLoading: LogInUiState

    data class IsSuccess(val result: Result<Unit>): LogInUiState

    data class IsFailed(val result: Result<Unit>): LogInUiState
}