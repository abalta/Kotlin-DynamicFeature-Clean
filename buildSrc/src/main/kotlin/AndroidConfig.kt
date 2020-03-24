object AndroidConfig {
    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val BUILD_TOOLS_VERSION = "29.0.2"

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    const val APPLICATION_ID = "com.mobiaxe.wasd"

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

object BuildType {
    const val RELEASE = "release"
    const val DEBUG = "debug"
}

object SigningConfig {
    object DEBUG {
        const val KEY_ALIAS = "androiddebugkey"
        const val KEY_PASSWORD = "android"
        const val STORE_PASSWORD = "android"
        const val STORE_FILE = "keystores/debug.keystore"
    }
}

