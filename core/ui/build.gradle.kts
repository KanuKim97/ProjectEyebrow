plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.ui"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = Versions.compose }
}

dependencies {
    implementation(Libraries.android_core)

    val composeBoM = platform(Libraries.compose_BoM)

    implementation(composeBoM)
    androidTestImplementation(composeBoM)
    implementation (Libraries.compose_Material3)
    implementation (Libraries.compose_UI)
    implementation (Libraries.compose_UI_Tooling)
    implementation (Libraries.compose_UI_Preview)
    implementation (Libraries.compose_Material_Icon)

    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.androidX_Junit)
    androidTestImplementation(Libraries.androidX_EspressoCore)
}