package com.binary.mobile.storage

import android.app.Application
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

private lateinit var app: Application

fun initStorage(application: Application) {
    app = application
}

actual fun createSettings(name: String): Settings =
    SharedPreferencesSettings(app.getSharedPreferences(name, android.content.Context.MODE_PRIVATE))
