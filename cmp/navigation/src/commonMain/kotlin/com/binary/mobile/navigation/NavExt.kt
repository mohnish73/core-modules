package com.binary.mobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigateSingleTop(
    route: String,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route) {
        launchSingleTop = true
        builder()
    }
}

fun NavController.navigateAndClearStack(route: String) {
    navigate(route) {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}

fun NavController.popOrNavigate(fallbackRoute: String) {
    if (!popBackStack()) navigateAndClearStack(fallbackRoute)
}
