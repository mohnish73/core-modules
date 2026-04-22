package com.binary.mobile.analytics

interface AnalyticsService {
    fun logEvent(event: AnalyticsEvent)
    fun setUserId(userId: String?)
    fun setUserProperty(key: String, value: String)
    fun logScreenView(screenName: String) = logEvent(AnalyticsEvent.screenView(screenName))
}

object NoOpAnalyticsService : AnalyticsService {
    override fun logEvent(event: AnalyticsEvent) = Unit
    override fun setUserId(userId: String?) = Unit
    override fun setUserProperty(key: String, value: String) = Unit
}

class CompositeAnalyticsService(
    private val services: List<AnalyticsService>,
) : AnalyticsService {
    override fun logEvent(event: AnalyticsEvent)          = services.forEach { it.logEvent(event) }
    override fun setUserId(userId: String?)               = services.forEach { it.setUserId(userId) }
    override fun setUserProperty(key: String, value: String) = services.forEach { it.setUserProperty(key, value) }
}
