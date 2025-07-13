plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    kotlin("android") apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.nexus.publish)
    cleanup
    base
}

allprojects {
    val GROUP: String by project
    val VERSION: String by project
    val USE_SNAPSHOT: String? by project
    group = GROUP
    version = if (USE_SNAPSHOT.toBoolean()) "$VERSION-SNAPSHOT" else VERSION
}

val detektFormatting = libs.detekt.formatting

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        config.from(rootProject.files("config/detekt/detekt.yml"))
    }

    dependencies {
        detektPlugins(detektFormatting)
    }
}

val NEXUS_USERNAME: String? by project
val NEXUS_PASSWORD: String? by project

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(NEXUS_USERNAME)
            password.set(NEXUS_PASSWORD)
        }
    }
}
