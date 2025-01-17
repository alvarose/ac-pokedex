import com.ase.plugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DiLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("ase.di.library")
            dependencies {
                add("implementation", libs.findLibrary("koin.compose").get())
            }
        }
    }
}
