object GradlePluginVersion {
    const val ANDROID_GRADLE = "3.5.3"
    const val GRADLE_VERSION_PLUGIN = "0.28.0"
}

object GradlePluginId {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_ANDROID_EXTENSIONS = "org.jetbrains.kotlin.android.extensions"
    const val GRADLE_VERSION_PLUGIN = "com.github.ben-manes.versions"
    const val KOTLIN_KAPT = "kotlin-kapt"
}

object GradleClasspath {
    const val GRADLE_PLUGIN = "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${CoreVersion.KOTLIN}"
}