package com.binary.mobile.storage

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

// NSUserDefaults(suiteName:) is a failable Objective-C initializer — Kotlin 2.x
// correctly exposes it as nullable. Fall back to standardUserDefaults if null.
actual fun createSettings(name: String): Settings {
    val defaults = NSUserDefaults(suiteName = name) ?: NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(defaults)
}
