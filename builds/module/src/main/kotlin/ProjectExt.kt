import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.TaskContainerScope
import org.gradle.kotlin.dsl.getByType

val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.getLib(name: String) = findLibrary(name).get()

fun DependencyHandlerScope.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

inline operator fun TaskContainer.invoke(
    configuration: TaskContainerScope.() -> Unit
): TaskContainer = apply {
    configuration(TaskContainerScope.of(this))
}