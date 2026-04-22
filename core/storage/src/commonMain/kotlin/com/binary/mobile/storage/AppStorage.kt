package com.binary.mobile.storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.flow.Flow

class AppStorage(private val settings: Settings) {

    private val flow = settings.toFlowSettings()

    fun putString(key: String, value: String)   = settings.putString(key, value)
    fun putInt(key: String, value: Int)          = settings.putInt(key, value)
    fun putLong(key: String, value: Long)        = settings.putLong(key, value)
    fun putBoolean(key: String, value: Boolean)  = settings.putBoolean(key, value)
    fun putFloat(key: String, value: Float)      = settings.putFloat(key, value)

    fun getString(key: String, default: String = "")      = settings.getString(key, default)
    fun getInt(key: String, default: Int = 0)             = settings.getInt(key, default)
    fun getLong(key: String, default: Long = 0L)          = settings.getLong(key, default)
    fun getBoolean(key: String, default: Boolean = false) = settings.getBoolean(key, default)
    fun getFloat(key: String, default: Float = 0f)        = settings.getFloat(key, default)

    fun getStringOrNull(key: String)  = settings.getStringOrNull(key)
    fun getIntOrNull(key: String)     = settings.getIntOrNull(key)
    fun getLongOrNull(key: String)    = settings.getLongOrNull(key)
    fun getBooleanOrNull(key: String) = settings.getBooleanOrNull(key)

    fun observeString(key: String, default: String = ""): Flow<String>   = flow.getStringFlow(key, default)
    fun observeInt(key: String, default: Int = 0): Flow<Int>             = flow.getIntFlow(key, default)
    fun observeBoolean(key: String, default: Boolean = false): Flow<Boolean> = flow.getBooleanFlow(key, default)

    fun remove(key: String) = settings.remove(key)
    fun clear()             = settings.clear()
    fun hasKey(key: String) = settings.hasKey(key)
}
