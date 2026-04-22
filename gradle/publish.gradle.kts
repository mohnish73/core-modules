// Applied to every KMP module — provides publishing config
apply(plugin = "maven-publish")

val sdkGroup   = project.properties["sdk.group"]   as String
val sdkVersion = project.properties["sdk.version"]  as String

group   = sdkGroup
version = sdkVersion

configure<PublishingExtension> {
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
