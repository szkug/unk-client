import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * @PluginId org.szkug.unk.kmp.lib
 */
class KMPLibConfigurationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configPlugin()
        configExtension()
    }


    private fun Project.configPlugin() = with(pluginManager) {
        apply("org.jetbrains.kotlin.multiplatform")
    }

    private fun Project.configExtension() = with(extensions) {
        configure<KotlinMultiplatformExtension> {
            applyDefaultHierarchyTemplate()
            jvm() // MARK: jvm target could be desktop & android library
            iosX64()
            iosArm64()
            iosSimulatorArm64()

            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project(":libs:common"))
                }
            }
        }
    }
}