import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get

class AndroidLibConfigurationPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configPlugin()
        configExtension()
    }

    private fun Project.configPlugin() = with(pluginManager) {
        apply("com.android.library")
    }

    private fun Project.configExtension() = with(extensions) {
        configure<LibraryExtension> {
            compileSdk = AndroidSdkVersions.COMPILE
            defaultConfig {
                minSdk = AndroidSdkVersions.MIN
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables.useSupportLibrary = true
            }

            compileOptions {
                sourceCompatibility = LanguageVersion.JAVA_SOURCE_COMPAT
                targetCompatibility = LanguageVersion.JAVA_TARGET_COMPAT
            }

            sourceSets["main"].apply {
                java.srcDirs("src/main/java", "src/main/kotlin")
            }
        }
    }
}