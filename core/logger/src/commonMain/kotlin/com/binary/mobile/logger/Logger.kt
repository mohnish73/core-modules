package com.binary.mobile.logger

object Logger {
    var defaultTag: String = "MobileSDK"
    var minLevel: LogLevel = LogLevel.VERBOSE

    fun v(message: String, tag: String = defaultTag) = write(LogLevel.VERBOSE, tag, message)
    fun d(message: String, tag: String = defaultTag) = write(LogLevel.DEBUG,   tag, message)
    fun i(message: String, tag: String = defaultTag) = write(LogLevel.INFO,    tag, message)
    fun w(message: String, tag: String = defaultTag) = write(LogLevel.WARN,    tag, message)
    fun e(message: String, throwable: Throwable? = null, tag: String = defaultTag) =
        write(LogLevel.ERROR, tag, message, throwable)

    private fun write(level: LogLevel, tag: String, message: String, throwable: Throwable? = null) {
        if (level >= minLevel) platformLog(level, tag, message, throwable)
    }
}

internal expect fun platformLog(level: LogLevel, tag: String, message: String, throwable: Throwable?)
