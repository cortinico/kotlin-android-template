version = LibraryKotlinCoordinates.LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    publish
}

dependencies {
    testImplementation(TestingLib.JUNIT)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
