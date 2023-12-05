plugins {
    id("com.gradle.enterprise") version "3.16"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "gradle-build-scan-quickstart"

gradleEnterprise {
    buildScan {
        server = "<<your Gradle Enterprise instance>>"
        publishAlways()
    }
}
