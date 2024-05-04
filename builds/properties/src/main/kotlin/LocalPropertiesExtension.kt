import org.gradle.api.Project
import java.util.Properties
import kotlin.io.path.div

private const val PROPERTIES_FILE_NAME = "local.properties"
private const val OUTPUT_DIR = "generated"

open class LocalPropertiesExtension(
    private val project: Project,
) {

    lateinit var packageName: String

    lateinit var properties: Map<String, String>

    fun readLocalProperties() = Properties().apply {
        load(project.rootProject.file(PROPERTIES_FILE_NAME).reader())
    }

    fun outputPath() = project.layout.buildDirectory.asFile.get().toPath() / OUTPUT_DIR // commonMain
}