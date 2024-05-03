import org.gradle.api.Plugin
import org.gradle.api.Project


class LocalPropertiesBuildPlugin : Plugin<Project> {

    private lateinit var extension: LocalPropertiesExtension

    override fun apply(target: Project) {
        extension = target.extensions.create("localProperties", LocalPropertiesExtension::class.java, target)
        val task = target.task("buildLocalProperties")
        task.group = "build"
        task.doLast {
            LocalPropertiesReader(target).build(extension)
        }
        target.tasks.getByName("build") {
            dependsOn(task)
        }
    }
}
