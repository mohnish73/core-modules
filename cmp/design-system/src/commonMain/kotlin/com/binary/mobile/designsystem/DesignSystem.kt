package com.binary.mobile.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object DesignSystem {
    val spacing: AppSpacing
        @Composable @ReadOnlyComposable get() = LocalAppSpacing.current
    val radius: AppRadius
        @Composable @ReadOnlyComposable get() = LocalAppRadius.current
}

@Composable
fun ProvideDesignSystem(
    spacing: AppSpacing = AppSpacing(),
    radius: AppRadius   = AppRadius(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppSpacing provides spacing,
        LocalAppRadius  provides radius,
        content         = content,
    )
}
