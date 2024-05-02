import com.squareup.kotlinpoet.*
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.internal.ensureParentDirsCreated
import java.io.File
import java.util.Properties
import kotlin.io.path.createDirectories
import kotlin.io.path.div

private const val PROPERTIES_FILE_NAME = "local.properties"
private const val OUTPUT_DIR = "generated"

class LocalPropertiesReader(private val project: Project) {

    // property name - field name
    fun build(
        vararg reads: Pair<String, String>
    ) {

        val group = project.group as String
        check(group.isNotBlank())

        val properties = Properties()
        properties.load(project.rootProject.file(PROPERTIES_FILE_NAME).reader())

        val values = mutableMapOf<String, String>()

        reads.forEach {
            values[it.second] = properties.getProperty(it.first)
        }

        val clasName = ClassName(group, "LocalProperties")

        val clazz = TypeSpec.objectBuilder(clasName).apply {
            values.forEach { (name, value) ->
                val property = PropertySpec.builder(name, String::class)
                    .addModifiers(KModifier.CONST)
                    .initializer("%S", value).build()
                addProperty(property)
            }
        }.build()

        val fileSpec = FileSpec.builder(clasName).addType(clazz).build()

        val path = project.layout.buildDirectory.asFile.get().toPath() /
                OUTPUT_DIR

        val file = path.toFile()
        file.ensureParentDirsCreated()

        fileSpec.writeTo(file)
    }
}