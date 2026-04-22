plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    id("maven-publish")
}

apply(from = rootProject.file("gradle/publish.gradle.kts"))

kotlin {
    jvmToolchain(17)
    androidLibrary {
        namespace  = "com.binary.mobile.serialization"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk     = libs.versions.android.minSdk.get().toInt()
    }
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        artifactId = when (name) {
            "kotlinMultiplatform" -> "core-serialization"
            else                  -> "core-serialization-$name"
        }
    }
}
