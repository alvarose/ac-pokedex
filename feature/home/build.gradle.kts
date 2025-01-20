plugins {
    id("ase.android.feature")
    id("ase.di.library.compose")
}

android {
    namespace = "com.ase.pokedex.ui.screens.home"
}

dependencies {
    implementation(project(":domain:pokemon"))
}
