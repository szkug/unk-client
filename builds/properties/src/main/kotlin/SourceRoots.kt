import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.reflect.Array as JavaArray

/**
 * @return A list of source roots and their dependencies.
 *
 * Examples:
 *   Multiplatform Environment. Ios target labeled "ios".
 *     -> iosMain deps [commonMain]
 *
 *   Android environment. internal, production, release, debug variants.
 *     -> internalDebug deps [internal, debug, main]
 *     -> internalRelease deps [internal, release, main]
 *     -> productionDebug deps [production, debug, main]
 *     -> productionRelease deps [production, release, main]
 *
 *    Multiplatform environment with android target (oh boy)
 */

// The signature of this function changed in Kotlin 1.7, so we invoke it reflectively to support
// both.
// 1.6.x: `fun source(vararg sources: Any): SourceTask`
// 1.7.x: `fun source(vararg sources: Any)`
private val SOURCE_FUNCTION = KotlinCompile::class.java.getMethod(
    "source",
    JavaArray.newInstance(Any::class.java, 0).javaClass,
)


context(LocalPropertiesBuildPlugin)
internal fun registerGenerateSource(project: Project, extension: LocalPropertiesExtension) {
    project.tasks
        .withType(KotlinCompile::class.java).configureEach {
            // Note that [KotlinCompile.source] will process files but will ignore strings.
            SOURCE_FUNCTION.invoke(this, arrayOf(extension.outputPath()))
        }
}

context(LocalPropertiesBuildPlugin)
internal fun registerGenerateTask(project: Project, task: TaskProvider<LocalPropertiesBuildTask>) {
    project.tasks
        .withType(AbstractKotlinCompileTool::class.java).configureEach {
            dependsOn(task)
        }
}