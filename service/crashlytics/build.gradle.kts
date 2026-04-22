plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

apply(from = rootProject.file("gradle/publish.gradle.kts"))

kotlin {
    jvmToolchain(17)
    androidLibrary {
        namespace  = "com.binary.mobile.crashlytics"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk     = libs.versions.android.minSdk.get().toInt()
    }
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        artifactId = when (name) {
            "kotlinMultiplatform" -> "service-crashlytics"
            else                  -> "service-crashlytics-$name"
        }
    }
}
