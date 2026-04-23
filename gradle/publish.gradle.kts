// Applied to every KMP module — provides publishing config
apply(plugin = "maven-publish")

val sdkGroup   = project.properties["sdk.group"]         as String
val sdkVersion = project.properties["sdk.version"]        as String
val ghOwner    = project.properties["sdk.github.owner"]   as String
val ghRepo     = project.properties["sdk.github.repo"]    as String

group   = sdkGroup
version = sdkVersion

configure<PublishingExtension> {
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

// GitHub Packages Maven does not support .klib artifacts (Kotlin/Native iOS targets)
// and returns 422 Unprocessable Entity for them. Disable all iOS target publications
// to GitHub Packages — the root kotlinMultiplatform metadata publication is sufficient
// for Android/KMP consumers resolving via Gradle.
afterEvaluate {
    tasks.withType<PublishToMavenRepository>().configureEach {
        if (repository.name == "GitHubPackages" && publication.name.startsWith("ios")) {
            enabled = false
        }
    }
}
