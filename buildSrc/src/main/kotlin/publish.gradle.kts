import com.android.build.gradle.LibraryExtension
import org.gradle.api.tasks.bundling.Jar

/**
 * Precompiled script plugin from:
 * https://github.com/cortinico/kotlin-android-template/blob/master/buildSrc/src/main/kotlin/publish.gradle.kts
 *
 * The following plugin tasks care of setting up:
 * - Publishing to Maven Central and Sonatype Snapshots
 * - GPG Signing with in memory PGP Keys
 * - Javadoc/SourceJar are attached via AGP
 *
 * To use it just apply:
 *
 * plugins {
 *     publish
 * }
 *
 * To your build.gradle.kts.
 *
 * If you copy over this file in your project, make sure to copy it inside: buildSrc/src/main/kotlin/publish.gradle.kts.
 * Make sure to copy over also buildSrc/build.gradle.kts otherwise this plugin will fail to compile due to missing dependencies.
 */
plugins {
    id("maven-publish")
    id("signing")
}

publishing {
    repositories {
        maven {
            name = "nexus"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = "NEXUS_USERNAME".byProperty
                password = "NEXUS_PASSWORD".byProperty
            }
        }
        maven {
            name = "snapshot"
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
            credentials {
                username = "NEXUS_USERNAME".byProperty
                password = "NEXUS_PASSWORD".byProperty
            }
        }
    }

    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                if (plugins.hasPlugin("com.android.library")) {
                    from(components["release"])
                } else {
                    from(components["java"])
                }
            }

            pom {
                if (!"USE_SNAPSHOT".byProperty.isNullOrBlank()) {
                    version = "$version-SNAPSHOT"
                }
                description.set("A template for Kotlin Android projects")
                url.set("https://github.com/cortinico/kotlin-android-template/")

                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("cortinico")
                        name.set("Nicola Corti")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/cortinico/kotlin-android-template.git")
                    developerConnection.set("scm:git:ssh://github.com/cortinico/kotlin-android-template.git")
                    url.set("https://github.com/cortinico/kotlin-android-template/")
                }
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/cortinico/kotlin-android-template/issues")
                }
            }
        }
    }

    val signingKey = "SIGNING_KEY".byProperty
    val signingPwd = "SIGNING_PWD".byProperty
    if (signingKey.isNullOrBlank() || signingPwd.isNullOrBlank()) {
        logger.info("Signing Disable as the PGP key was not found")
    } else {
        logger.info("GPG Key found - Signing enabled")
        signing {
            useInMemoryPgpKeys(signingKey, signingPwd)
            sign(publishing.publications["release"])
        }
    }
}


val String.byProperty: String? get() = findProperty(this) as? String
