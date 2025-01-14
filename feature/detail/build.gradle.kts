plugins {
    id("ase.android.feature")
}

android {
    namespace = "com.ase.pokedex.ui.screens.detail"
}

dependencies {
    implementation(project(":domain:pokemon"))
}
