package com.binary.mobile.crashlytics

interface CrashReporter {
    fun recordException(throwable: Throwable)
    fun recordException(throwable: Throwable, context: Map<String, String>)
    fun log(message: String)
    fun setUserId(userId: String?)
    fun setCustomKey(key: String, value: String)
    fun setCustomKey(key: String, value: Boolean)
    fun setCustomKey(key: String, value: Int)
    fun setCrashCollectionEnabled(enabled: Boolean)
}

object NoOpCrashReporter : CrashReporter {
    override fun recordException(throwable: Throwable) = Unit
    override fun recordException(throwable: Throwable, context: Map<String, String>) = Unit
    override fun log(message: String) = Unit
    override fun setUserId(userId: String?) = Unit
    override fun setCustomKey(key: String, value: String) = Unit
    override fun setCustomKey(key: String, value: Boolean) = Unit
    override fun setCustomKey(key: String, value: Int) = Unit
    override fun setCrashCollectionEnabled(enabled: Boolean) = Unit
}
