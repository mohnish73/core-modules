package com.binary.mobile.ui.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

enum class AppToastDuration(val millis: Long) { Short(2000L), Long(3500L) }

class AppToastController {
    var message: String? by mutableStateOf(null)
        private set
    var duration: AppToastDuration by mutableStateOf(AppToastDuration.Short)
        private set

    fun show(message: String, duration: AppToastDuration = AppToastDuration.Short) {
        this.message = message
        this.duration = duration
    }

    fun dismiss() { message = null }
}

@Composable
fun rememberAppToastController(): AppToastController = remember { AppToastController() }

@Composable
fun AppToastHost(
    controller: AppToastController,
    modifier: Modifier = Modifier,
) {
    val msg = controller.message
    AppToast(
        message = msg ?: "",
        visible = msg != null,
        duration = controller.duration,
        onDismiss = { controller.dismiss() },
        modifier = modifier,
    )
}

@Composable
fun AppToast(
    message: String,
    visible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    duration: AppToastDuration = AppToastDuration.Short,
) {
    LaunchedEffect(visible, message) {
        if (visible) {
            delay(duration.millis)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it }),
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.inverseSurface,
                tonalElevation = 6.dp,
            ) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                )
            }
        }
    }
}
