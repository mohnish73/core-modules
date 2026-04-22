package com.binary.mobile.ui.snackbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun rememberAppSnackbarState(): SnackbarHostState = remember { SnackbarHostState() }

@Composable
fun AppSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(hostState = hostState, modifier = modifier) { data ->
        AppSnackbar(data = data)
    }
}

@Composable
fun AppSnackbar(data: SnackbarData) {
    Snackbar(
        snackbarData = data,
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        actionColor = MaterialTheme.colorScheme.inversePrimary,
        dismissActionContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        shape = MaterialTheme.shapes.small,
    )
}

suspend fun SnackbarHostState.showAppSnackbar(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
): SnackbarResult = showSnackbar(message = message, actionLabel = actionLabel, duration = duration)
