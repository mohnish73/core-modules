package com.binary.mobile.ui.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class AppButtonStyle { Primary, Secondary, Text }

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: AppButtonStyle = AppButtonStyle.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val content: @Composable RowScope.() -> Unit = {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp,
                color = when (style) {
                    AppButtonStyle.Primary -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.primary
                },
            )
        } else {
            leadingIcon?.let {
                it()
                Spacer(Modifier.width(8.dp))
            }
            Text(text = text)
        }
    }

    when (style) {
        AppButtonStyle.Primary -> Button(
            onClick = onClick,
            enabled = enabled && !loading,
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
            content = content,
        )
        AppButtonStyle.Secondary -> OutlinedButton(
            onClick = onClick,
            enabled = enabled && !loading,
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
            content = content,
        )
        AppButtonStyle.Text -> TextButton(
            onClick = onClick,
            enabled = enabled && !loading,
            modifier = modifier,
            content = content,
        )
    }
}
