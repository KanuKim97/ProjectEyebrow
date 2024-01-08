plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_ksp)
    id(Plugins.dagger_hilt)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.database"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Libraries.android_core)

    implementation (Libraries.room_Runtime)
    ksp (Libraries.room_Compiler)
    implementation (Libraries.room_ktx)

    implementation(Libraries.hilt_Android)
    ksp(Libraries.hilt_Android_Compiler)

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidX_Junit)
}