package com.binary.mobile.logger

import platform.Foundation.NSLog

internal actual fun platformLog(level: LogLevel, tag: String, message: String, throwable: Throwable?) {
    val prefix = level.name.first()
    val error  = throwable?.let { " | ${it.message}" } ?: ""
    NSLog("[$prefix] $tag: $message$error")
}
