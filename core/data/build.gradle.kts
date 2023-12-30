plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_ksp)
    id(Plugins.dagger_hilt)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.data"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation (Libraries.android_core)

    implementation (project(":core:domain"))

    implementation (Libraries.kotlin_coroutine)

    implementation (Libraries.room_Runtime)
    ksp (Libraries.room_Compiler)
    implementation (Libraries.room_ktx)

    implementation (Libraries.hilt_Android)
    ksp (Libraries.hilt_Android_Compiler)

    val firebaseBoM = platform(Libraries.firebaseBoM)
    implementation(firebaseBoM)
    implementation(Libraries.firebase_Auth)
    implementation(Libraries.firebase_UI_Auth)
    implementation(Libraries.firebase_Analytics)
    implementation(Libraries.firebase_Storage)
    implementation(Libraries.firebase_Database)
    implementation(Libraries.firebase_Storage)

    implementation(Libraries.gms_play_service_Auth)

    testImplementation (Libraries.jUnit)
    androidTestImplementation (Libraries.androidX_Junit)
    androidTestImplementation (Libraries.androidX_EspressoCore)
}