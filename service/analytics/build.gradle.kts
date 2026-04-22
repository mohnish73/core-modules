plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

apply(from = rootProject.file("gradle/publish.gradle.kts"))

kotlin {
    jvmToolchain(17)
    androidLibrary {
        namespace  = "com.binary.mobile.analytics"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk     = libs.versions.android.minSdk.get().toInt()
    }
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        artifactId = when (name) {
            "kotlinMultiplatform" -> "service-analytics"
            else                  -> "service-analytics-$name"
        }
    }
}
