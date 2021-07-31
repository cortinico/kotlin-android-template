plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

kotlinDslPluginOptions.experimentalWarning.set(false)

object Plugins {
    const val AGP = "7.0.0"
    const val DOKKA = "1.5.0"
    const val KOTLIN = "1.5.21"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Plugins.KOTLIN}")
    implementation("com.android.tools.build:gradle:${Plugins.AGP}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${Plugins.DOKKA}")
    implementation("org.jetbrains.dokka:dokka-core:${Plugins.DOKKA}")
}
