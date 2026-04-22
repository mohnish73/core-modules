package com.binary.mobile.animations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically

private const val DURATION_MS = 300

object AppTransitions {

    val slideInFromRight: EnterTransition =
        slideInHorizontally(tween(DURATION_MS)) { it } + fadeIn(tween(DURATION_MS))

    val slideOutToLeft: ExitTransition =
        slideOutHorizontally(tween(DURATION_MS)) { -it } + fadeOut(tween(DURATION_MS))

    val slideInFromLeft: EnterTransition =
        slideInHorizontally(tween(DURATION_MS)) { -it } + fadeIn(tween(DURATION_MS))

    val slideOutToRight: ExitTransition =
        slideOutHorizontally(tween(DURATION_MS)) { it } + fadeOut(tween(DURATION_MS))

    val slideInFromBottom: EnterTransition =
        slideInVertically(tween(DURATION_MS)) { it } + fadeIn(tween(DURATION_MS))

    val slideOutToBottom: ExitTransition =
        slideOutVertically(tween(DURATION_MS)) { it } + fadeOut(tween(DURATION_MS))

    val fadeIn: EnterTransition  = fadeIn(tween(DURATION_MS))
    val fadeOut: ExitTransition  = fadeOut(tween(DURATION_MS))

    val none: Pair<EnterTransition, ExitTransition> =
        EnterTransition.None to ExitTransition.None
}
