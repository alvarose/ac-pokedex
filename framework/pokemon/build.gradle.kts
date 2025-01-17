import java.util.Properties

plugins {
    id("ase.android.library")
    id("ase.android.room")
    id("ase.jvm.retrofit")
    id("ase.di.library")
}

android {
    namespace = "com.ase.pokedex.framework.pokemon"

    val props = Properties()
    props.load(project.rootProject.file("local.properties").readText().byteInputStream())

    defaultConfig {
        val apiUrl = "API_URL"
        buildConfigField("String", apiUrl, props.getProperty(apiUrl, ""))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain:pokemon"))
}
