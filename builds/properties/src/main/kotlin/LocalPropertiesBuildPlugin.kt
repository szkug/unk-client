import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized


private const val TASK_NAME_PREFIX = "generate"
private const val TASK_NAME_SUFFIX = "LocalProperties"
private const val TASK_ROOT = "generateLocalProperties"
private const val GROUP = "build"

class LocalPropertiesBuildPlugin : Plugin<Project> {

    private lateinit var extension: LocalPropertiesExtension

    override fun apply(target: Project) {
        extension = target.extensions.create("localProperties", LocalPropertiesExtension::class.java, target)

        target.afterEvaluate {
            target.registerTask()
        }
    }

    private fun Project.registerTask() {

//        val rootTask = tasks.register(TASK_ROOT) {
//            group = GROUP
//            description = "Aggregation task which runs every generation task for every given source"
//        }

        val task = tasks.register(TASK_ROOT, LocalPropertiesBuildTask::class.java) {
            group = GROUP
            extension.set(this@LocalPropertiesBuildPlugin.extension)
        }


        registerGenerateSource(this, extension)
        registerGenerateTask(this, task)
    }

}
