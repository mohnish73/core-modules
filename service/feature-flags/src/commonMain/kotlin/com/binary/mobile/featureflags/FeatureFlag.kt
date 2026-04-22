package com.binary.mobile.featureflags

data class FeatureFlag<T>(
    val key: String,
    val default: T,
)

object Flags {
    // Define your app-wide flags here and share across projects.
    // Example:
    // val NewOnboarding  = FeatureFlag("new_onboarding",  default = false)
    // val MaxUploadSizeMb = FeatureFlag("max_upload_mb",  default = 10)
}
