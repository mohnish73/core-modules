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
