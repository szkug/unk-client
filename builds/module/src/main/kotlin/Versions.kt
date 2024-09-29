import org.gradle.api.JavaVersion

object AndroidSdkVersions {
    // target sdk version
    const val TARGET = 34

    // compile sdk version
    const val COMPILE = 34

    // min sdk version
    const val MIN = 23

    // Build-Tools version
    const val BUILD_TOOLS = "34.0.0"

    // sdk extension version
    // https://developer.android.com/guide/sdk-extensions
    const val EXTENSION = 8
}

object LanguageVersion {

    // Need sync with KotlinCompile option jvmTarget in root build.gradle.kts
    val JAVA_SOURCE_COMPAT = JavaVersion.VERSION_17
    val JAVA_TARGET_COMPAT = JavaVersion.VERSION_17
}