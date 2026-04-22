package com.binary.mobile.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppThemeConfig = staticCompositionLocalOf<AppThemeConfig> {
    error("No AppThemeConfig provided — wrap your app with AppTheme { }")
}

data class AppThemeConfig(
    val lightColors: ColorScheme = lightColorScheme(),
    val darkColors: ColorScheme  = darkColorScheme(),
    val typography: Typography   = Typography(),
)

@Composable
fun AppTheme(
    config: AppThemeConfig = AppThemeConfig(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) config.darkColors else config.lightColors

    CompositionLocalProvider(LocalAppThemeConfig provides config) {
        MaterialTheme(
            colorScheme = colors,
            typography  = config.typography,
            content     = content,
        )
    }
}
