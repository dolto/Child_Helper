pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://packagecloud.io/biopassid/FingerprintSDKAndroid/maven2")
        maven("https://jitpack.io")
    }
}

rootProject.name = "child_helper"
include(":app")
