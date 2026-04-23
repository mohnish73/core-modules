plugins {
    id("java-platform")
    id("maven-publish")
}

val sdkGroup   = project.properties["sdk.group"]        as String
val sdkVersion = project.properties["sdk.version"]       as String
val ghOwner    = project.properties["sdk.github.owner"]  as String
val ghRepo     = project.properties["sdk.github.repo"]   as String

group   = sdkGroup
version = sdkVersion

dependencies {
    constraints {
        // KMP
        api("$sdkGroup:core-logger:$sdkVersion")
        api("$sdkGroup:core-utils:$sdkVersion")
        api("$sdkGroup:core-network:$sdkVersion")
        api("$sdkGroup:core-serialization:$sdkVersion")
        api("$sdkGroup:core-base-abstractions:$sdkVersion")
        api("$sdkGroup:core-storage:$sdkVersion")
        // CMP
        api("$sdkGroup:cmp-ui-components:$sdkVersion")
        api("$sdkGroup:cmp-themes:$sdkVersion")
        api("$sdkGroup:cmp-design-system:$sdkVersion")
api("$sdkGroup:cmp-animations:$sdkVersion")
        // Service
        api("$sdkGroup:service-analytics:$sdkVersion")
        api("$sdkGroup:service-crashlytics:$sdkVersion")
        api("$sdkGroup:service-permissions:$sdkVersion")
        api("$sdkGroup:service-push-notifications:$sdkVersion")
        api("$sdkGroup:service-feature-flags:$sdkVersion")
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
            url  = uri("https://maven.pkg.github.com/$ghOwner/$ghRepo")
            credentials {
                username = System.getenv("GITHUB_USERNAME") ?: ghOwner
                password = System.getenv("GITHUB_TOKEN")   ?: ""
            }
        }
    }
}
