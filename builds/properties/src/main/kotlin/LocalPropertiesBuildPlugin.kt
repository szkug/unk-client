import org.gradle.api.Plugin
import org.gradle.api.Project


class NetworkLocalBuildPropertiesPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        target.tasks.getByName("build").doFirst {
            LocalPropertiesReader(target).build(
                "network.host.dev" to "DEV_HOST",
                "network.host.release" to "RELEASE_HOST"
            )
        }
    }
}
