plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Sdk.MIN_SDK_VERSION
        targetSdk = Sdk.TARGET_SDK_VERSION

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

dependencies {
    implementation(project(":library-kotlin"))

    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)

    implementation(ComposeLibs.COMPOSE_UI)
    implementation(ComposeLibs.COMPOSE_MATERIAL)
    implementation(ComposeLibs.COMPOSE_ACTIVITY)
    implementation(ComposeLibs.COMPOSE_TOOLING)

    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.COMPOSE_TEST_JUNIT)
}
