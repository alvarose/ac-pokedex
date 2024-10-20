plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
}

val detektPluginId = libs.plugins.detekt.get().pluginId.toString()
val detektPluginVersion = libs.plugins.detekt.get().version.toString()
val detektFormattingPlugin = libs.detekt.formatting.get().toString()

subprojects {
    apply(plugin = detektPluginId)
    detekt {
        toolVersion = detektPluginVersion
        config.setFrom(file("${rootDir}/config/detekt/detekt.yml"))
        buildUponDefaultConfig = true

        dependencies {
            detektPlugins(detektFormattingPlugin)
        }
    }
}
