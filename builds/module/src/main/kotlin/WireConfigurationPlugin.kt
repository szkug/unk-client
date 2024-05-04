import com.squareup.wire.gradle.WireExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.buildscript
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.szkug.krpc.plugin.KrpcSchemaHandlerFactory

class WireConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        configPlugin()
        configExtension()
    }

    private fun Project.configPlugin() = with(pluginManager) {
        apply("com.squareup.wire")
    }

    private fun Project.configExtension() = with(extensions) {

        configure<WireExtension> {
            custom {
                schemaHandlerFactory = KrpcSchemaHandlerFactory.client()
                out = "${layout.buildDirectory.asFile.get()}/generated"
            }
        }

        configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation("org.szkug.krpc:runtime")
                }
            }
        }
    }
}