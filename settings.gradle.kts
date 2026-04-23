rootProject.name = "core-modules"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            mavenContent {
                includeGroupAndSubgroups("org.jetbrains")
            }
        }
        mavenCentral()
    }
}

include(":bom")

// ── KMP modules ──────────────────────────────────────────────────────────────
include(":core:logger")
include(":core:utils")
include(":core:network")
include(":core:serialization")
include(":core:base-abstractions")
include(":core:storage")

// ── CMP modules ──────────────────────────────────────────────────────────────
include(":cmp:ui-components")
include(":cmp:themes")
include(":cmp:design-system")
include(":cmp:navigation")
include(":cmp:animations")

// ── Service modules ───────────────────────────────────────────────────────────
include(":service:analytics")
include(":service:crashlytics")
include(":service:permissions")
include(":service:push-notifications")
include(":service:feature-flags")
