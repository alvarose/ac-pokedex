plugins {
    id("ase.android.library")
    id("ase.android.room")
    id("ase.jvm.retrofit")
}

android {
    namespace = "com.ase.pokedex.framework.pokemon"
}

dependencies {
    implementation(project(":domain:pokemon"))
}
