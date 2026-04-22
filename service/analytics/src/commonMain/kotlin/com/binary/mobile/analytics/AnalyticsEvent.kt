package com.binary.mobile.analytics

data class AnalyticsEvent(
    val name: String,
    val params: Map<String, Any> = emptyMap(),
) {
    companion object {
        fun screenView(screenName: String) = AnalyticsEvent(
            name   = "screen_view",
            params = mapOf("screen_name" to screenName),
        )
        fun buttonClick(buttonName: String, screen: String = "") = AnalyticsEvent(
            name   = "button_click",
            params = buildMap {
                put("button_name", buttonName)
                if (screen.isNotEmpty()) put("screen", screen)
            },
        )
        fun error(errorType: String, message: String) = AnalyticsEvent(
            name   = "app_error",
            params = mapOf("error_type" to errorType, "message" to message),
        )
    }
}
