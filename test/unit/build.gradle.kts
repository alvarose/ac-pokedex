plugins {
    id("ase.jvm.library")
}

dependencies {
    implementation(project(":domain:pokemon"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}
