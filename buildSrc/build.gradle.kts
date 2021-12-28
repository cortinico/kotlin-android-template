plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.kgp)
    implementation(libs.agp)
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.dokka.core)
}
