plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.KOTLIN_KAPT)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        buildConfigFieldFromGradleProperty("apiBaseUrl")
        buildConfigFieldFromGradleProperty("apiToken")
    }

    dataBinding {
        isEnabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(Library.KOTLIN)
    api(Library.MATERIAL)
    api(Library.VIEWPAGER2)
    api(Library.CONSTRAINT)
    api(Library.KOIN)
    api(Library.GLIDE)
    api(Library.PAGING)
    api(Library.COROUTINE)

    implementation(Library.RETROFIT)
    implementation(Library.RETROFIT_CONVERTER)
    implementation(Library.OKHTTP_LOGGING)
    implementation(Library.VIEWMODEL)
    implementation(Library.ROOM)
    implementation(Library.ROOM_KTX)
    implementation(Library.TIMBER)
    implementation(Library.STETHO_OKHTTP)

    debugImplementation(Library.LEAK_CANARY)

    kapt(Library.ROOM_COMPILER)
}

fun com.android.build.gradle.internal.dsl.BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

