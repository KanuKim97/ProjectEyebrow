buildscript {
    dependencies {
        classpath(ProjectDependencies.google_gms_services)
    }
}

plugins {
    id (Plugins.android_application) version Versions.gradleVersion apply false
    id (Plugins.android_library) version Versions.gradleVersion apply false
    id (Plugins.kotlin_android) version Versions.kotlin apply false
    id (Plugins.dagger_hilt) version Versions.hilt apply false
    id (Plugins.kotlin_ksp) version Versions.ksp apply false
    id (Plugins.kotlin_jvm) version Versions.kotlin apply false
}