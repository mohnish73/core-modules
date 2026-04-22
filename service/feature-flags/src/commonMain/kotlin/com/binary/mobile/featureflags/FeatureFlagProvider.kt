package com.binary.mobile.featureflags

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface FeatureFlagProvider {
    fun <T> getValue(flag: FeatureFlag<T>): T
    fun <T> observe(flag: FeatureFlag<T>): Flow<T>
    suspend fun fetchAndActivate(): Boolean
}

class LocalFeatureFlagProvider(
    private val overrides: Map<String, Any> = emptyMap(),
) : FeatureFlagProvider {

    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue(flag: FeatureFlag<T>): T =
        (overrides[flag.key] as? T) ?: flag.default

    override fun <T> observe(flag: FeatureFlag<T>): Flow<T> =
        flowOf(getValue(flag))

    override suspend fun fetchAndActivate(): Boolean = true
}
