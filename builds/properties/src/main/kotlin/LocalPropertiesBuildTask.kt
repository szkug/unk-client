import com.squareup.kotlinpoet.*
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.internal.ensureParentDirsCreated


@CacheableTask
abstract class LocalPropertiesBuildTask : DefaultTask() {

    @get:Input
    abstract val extension: Property<LocalPropertiesExtension>

    @TaskAction
    fun build() {

        val extension = extension.get()

        println("LocalPropertiesBuildTask build ${extension.properties}")

        check(extension.packageName.isNotBlank())

        val properties = extension.readLocalProperties()

        val values = mutableMapOf<String, String>()

        extension.properties.forEach {
            values[it.value] = properties.getProperty(it.key)
        }

        val clasName = ClassName(extension.packageName, "LocalProperties")

        val clazz = TypeSpec.objectBuilder(clasName).apply {
            values.forEach { (name, value) ->
                val property = PropertySpec.builder(name, String::class)
                    .addModifiers(KModifier.CONST)
                    .initializer("%S", value).build()
                addProperty(property)
            }
        }.build()

        val fileSpec = FileSpec.builder(clasName).addType(clazz).build()

        val path = extension.outputPath()

        val file = path.toFile()
        file.ensureParentDirsCreated()

        fileSpec.writeTo(file)
    }
}