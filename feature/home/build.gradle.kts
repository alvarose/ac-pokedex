plugins {
    id("ase.android.feature")
}

android {
    namespace = "com.ase.pokedex.ui.screens.home"
}

dependencies {
    implementation(project(":domain:pokemon"))
}
