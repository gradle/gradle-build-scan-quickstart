pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven {
            url = uri("https://repo.grdev.net/artifactory/public")
            credentials {
                settings.extra.apply {
                    username = System.getenv("ARTIFACTORY_USERNAME") ?: get(if (has("gradleInternalRepositoryUsername")) "gradleInternalRepositoryUsername" else "enterprise.snapshots.username").toString()
                    password = System.getenv("ARTIFACTORY_PASSWORD") ?: get(if (has("gradleInternalRepositoryPassword")) "gradleInternalRepositoryPassword" else "enterprise.snapshots.password").toString()
                }
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
        maven {
            url = uri("https://repo.grdev.net/artifactory/enterprise-libs-release-candidates-local/")
        }
    }

}

plugins {
    id("com.gradle.develocity") version "3.19-rc-1"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "gradle-build-scan-quickstart"

develocity {
    server = "https://develocity.grdev.net"
    allowUntrustedServer = true
    edgeDiscovery = true
    buildCache {
       local {
         isEnabled = false
       } 
       remote(develocity.buildCache) {
         isPush = true
       }
    }
}
