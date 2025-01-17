plugins {
    id("ase.android.library.compose")
}

android {
    namespace = "com.ase.pokedex.ui.common"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.lottie)
}
