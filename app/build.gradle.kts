import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    id("ase.android.application")
    id("ase.android.application.compose")
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val props = Properties()
        props.load(project.rootProject.file("local.properties").readText().byteInputStream())

        val apiUrl = "API_URL"
        buildConfigField("String", apiUrl, props.getProperty(apiUrl, ""))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Google
    implementation(libs.google.services.location)

    // Core
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
