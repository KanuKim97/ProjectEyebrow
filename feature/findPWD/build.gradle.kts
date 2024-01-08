plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.findpwd"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Libraries.android_core)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidX_Junit)
    androidTestImplementation(Libraries.androidX_EspressoCore)
}