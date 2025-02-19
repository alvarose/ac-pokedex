import com.android.build.api.dsl.ApplicationExtension
import com.ase.plugins.addAndroidTestDependencies
import com.ase.plugins.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }

            addAndroidTestDependencies()
        }
    }
}
