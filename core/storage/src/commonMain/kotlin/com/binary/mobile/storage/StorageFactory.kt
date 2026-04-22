package com.binary.mobile.storage

import com.russhwolf.settings.Settings

expect fun createSettings(name: String): Settings

fun createAppStorage(name: String = "app_prefs"): AppStorage = AppStorage(createSettings(name))
