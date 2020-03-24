import extension.getLocalProperty

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.KOTLIN_KAPT)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    signingConfigs {
        create(BuildType.RELEASE) {
            keyAlias = getLocalProperty("keyAlias")
            keyPassword = getLocalProperty("keyPassword")
            storeFile = file(getLocalProperty("storeFile"))
            storePassword = getLocalProperty("storePassword")
        }
        getByName(BuildType.DEBUG) {
            keyAlias = SigningConfig.DEBUG.KEY_ALIAS
            keyPassword = SigningConfig.DEBUG.KEY_PASSWORD
            storeFile = file(SigningConfig.DEBUG.STORE_FILE)
            storePassword = SigningConfig.DEBUG.STORE_PASSWORD
        }
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isMinifyEnabled = false
            isDebuggable = true
            signingConfig = signingConfigs.getByName(BuildType.DEBUG)
        }

        getByName(BuildType.RELEASE) {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName(BuildType.RELEASE)
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile(file("proguard-rules.pro"))
        }
    }

    dataBinding {
        isEnabled = true
    }

    dynamicFeatures = mutableSetOf(
        ModuleDependency.FEATURE_ONBOARDING,
        ModuleDependency.FEATURE_HOME)
}

dependencies {
    api(project(ModuleDependency.CORE))

    implementation(Library.NAVIGATION_FRAGMENT_KTX)
    implementation(Library.NAVIGATION_DYNAMIC_FEATURE)
    implementation(Library.NAVIGATION_UI_KTX)

    implementation(Library.STETHO)
}
