package com.binary.mobile.ui.flashbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

enum class FlashbarType { Success, Error, Warning, Info }

data class FlashbarData(
    val message: String,
    val type: FlashbarType = FlashbarType.Info,
    val actionLabel: String? = null,
    val onAction: (() -> Unit)? = null,
    val autoDismissMs: Long = 4000L,
)

class FlashbarController {
    var current: FlashbarData? by mutableStateOf(null)
        private set

    fun show(data: FlashbarData) { current = data }

    fun show(
        message: String,
        type: FlashbarType = FlashbarType.Info,
        actionLabel: String? = null,
        onAction: (() -> Unit)? = null,
        autoDismissMs: Long = 4000L,
    ) = show(FlashbarData(message, type, actionLabel, onAction, autoDismissMs))

    fun dismiss() { current = null }
}

@Composable
fun rememberFlashbarController(): FlashbarController = remember { FlashbarController() }

@Composable
fun FlashbarHost(
    controller: FlashbarController,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = controller.current != null,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it }),
        modifier = modifier,
    ) {
        controller.current?.let { data ->
            LaunchedEffect(data) {
                if (data.autoDismissMs > 0) {
                    delay(data.autoDismissMs)
                    controller.dismiss()
                }
            }
            AppFlashbar(data = data, onDismiss = { controller.dismiss() })
        }
    }
}

@Composable
fun AppFlashbar(
    data: FlashbarData,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (bgColor, contentColor, icon) = flashbarColors(data.type)

    Surface(
        color = bgColor,
        contentColor = contentColor,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier)
            Text(
                text = data.message,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
            )
            data.actionLabel?.let { label ->
                TextButton(onClick = { data.onAction?.invoke(); onDismiss() }) {
                    Text(label, color = contentColor)
                }
                Spacer(Modifier.width(4.dp))
            }
            IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Close, contentDescription = "Dismiss")
            }
        }
    }
}

private data class FlashbarColors(val bg: Color, val content: Color, val icon: ImageVector)

@Composable
private fun flashbarColors(type: FlashbarType): FlashbarColors {
    val colorScheme = MaterialTheme.colorScheme
    return when (type) {
        FlashbarType.Success -> FlashbarColors(
            bg = Color(0xFF2E7D32),
            content = Color.White,
            icon = Icons.Default.CheckCircle,
        )
        FlashbarType.Error -> FlashbarColors(
            bg = colorScheme.errorContainer,
            content = colorScheme.onErrorContainer,
            icon = Icons.Default.Warning,
        )
        FlashbarType.Warning -> FlashbarColors(
            bg = Color(0xFFF57F17),
            content = Color.White,
            icon = Icons.Default.Warning,
        )
        FlashbarType.Info -> FlashbarColors(
            bg = colorScheme.primaryContainer,
            content = colorScheme.onPrimaryContainer,
            icon = Icons.Default.Info,
        )
    }
}
