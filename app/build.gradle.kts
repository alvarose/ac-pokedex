plugins {
    id("ase.android.application")
    id("ase.android.application.compose")
    id("ase.di.library.compose")
}

android {
    namespace = "com.ase.pokedex"

    val versionMajor = 1
    val versionMinor = 0
    val versionPatch = 0

    defaultConfig {
        applicationId = "com.ase.pokedex"
        versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        setProperty("archivesBaseName", "pokedex-v$versionName")

        testInstrumentationRunner = "com.ase.pokedex.di.HiltTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // Modules
    implementation(project(":domain:pokemon"))
    implementation(project(":framework:pokemon"))
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:common"))

    // Coil
    implementation(libs.coil.compose)

    // Google
    implementation(libs.google.services.location)

    // Core
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.room.ktx)
    kspAndroidTest(libs.room.compiler)
    androidTestImplementation(libs.okhttp.mockwebserver)
}
