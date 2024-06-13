plugins {
    id("com.gradle.develocity") version "3.17.5"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "gradle-build-scan-quickstart"

develocity.server = "<<your Gradle Enterprise instance>>"
