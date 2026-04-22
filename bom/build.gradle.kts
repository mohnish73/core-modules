plugins {
    id("java-platform")
    id("maven-publish")
}

val sdkGroup   = project.properties["sdk.group"]   as String
val sdkVersion = project.properties["sdk.version"]  as String

group   = sdkGroup
version = sdkVersion

dependencies {
    constraints {
        api("$sdkGroup:core-logger:$sdkVersion")
        api("$sdkGroup:core-utils:$sdkVersion")
        api("$sdkGroup:core-network:$sdkVersion")
    }
}

publishing {
    publications {
        create<MavenPublication>("bom") {
            artifactId = "mobile-bom"
            from(components["javaPlatform"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url  = uri("https://maven.pkg.github.com/${System.getenv("GITHUB_USERNAME") ?: ""}/core-modules")
            credentials {
                username = System.getenv("GITHUB_USERNAME") ?: ""
                password = System.getenv("GITHUB_TOKEN")   ?: ""
            }
        }
    }
}
