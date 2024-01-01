plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_ksp)
    id(Plugins.dagger_hilt)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.network"
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

    /* -- FireBase -- */
    val firebaseBom = platform(Libraries.firebaseBoM)
    implementation(firebaseBom)
    implementation(Libraries.firebase_Auth)
    implementation(Libraries.firebase_UI_Auth)
    implementation(Libraries.firebase_Analytics)
    implementation(Libraries.firebase_Storage)
    implementation(Libraries.firebase_Database)
    implementation(Libraries.fireStore)

    implementation(Libraries.gms_play_service_Auth)

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidX_Junit)
}