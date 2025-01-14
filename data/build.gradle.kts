plugins {
    id("ase.android.library")
    id("ase.android.room")
    id("ase.jvm.retrofit")
}

android {
    namespace = "com.ase.pokedex.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
