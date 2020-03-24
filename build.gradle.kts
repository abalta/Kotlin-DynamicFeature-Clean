import extension.applyDefaults

plugins {
    id(GradlePluginId.GRADLE_VERSION_PLUGIN) version GradlePluginVersion.GRADLE_VERSION_PLUGIN
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(GradleClasspath.GRADLE_PLUGIN)
        classpath(GradleClasspath.KOTLIN_GRADLE_PLUGIN)
    }
}

allprojects {
    repositories.applyDefaults()
}

tasks {
    register("clean", Delete::class) {
        delete = setOf(rootProject.buildDir)
    }
}
