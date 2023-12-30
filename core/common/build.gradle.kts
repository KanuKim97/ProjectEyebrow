plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_ksp)
    id(Plugins.dagger_hilt)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.common"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}


dependencies {
    implementation(Libraries.android_core)

    implementation(Libraries.hilt_Android)
    ksp(Libraries.hilt_Android_Compiler)

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidX_Junit)
}