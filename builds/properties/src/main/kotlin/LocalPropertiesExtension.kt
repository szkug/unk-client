import org.gradle.api.Project

open class LocalPropertiesExtension(
    private val project: Project,
) {

    lateinit var packageName: String

    lateinit var properties: Map<String, String>
}