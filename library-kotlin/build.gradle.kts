version = LibraryKotlinCoordinates.LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    publish
}

dependencies {
    testImplementation(libs.junit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}
