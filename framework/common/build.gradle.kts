plugins {
    id("ase.android.library")
}

android {
    namespace = "com.ase.pokedex.framework.pokemon"
}

dependencies {
    implementation(project(":domain:pokemon"))
}
