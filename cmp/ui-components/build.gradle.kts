plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    id("maven-publish")
}

apply(from = rootProject.file("gradle/publish.gradle.kts"))

kotlin {
    jvmToolchain(17)
    androidLibrary {
        namespace  = "com.binary.mobile.ui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk     = libs.versions.android.minSdk.get().toInt()
    }
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.animation)
            implementation(compose.components.uiToolingPreview)
        }
        androidMain.dependencies {
            implementation(compose.uiTooling)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        artifactId = when (name) {
            "kotlinMultiplatform" -> "cmp-ui-components"
            else                  -> "cmp-ui-components-$name"
        }
    }
}
