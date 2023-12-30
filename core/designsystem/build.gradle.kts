plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.designsystem"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.3" }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")

    val composeBoM = platform("androidx.compose:compose-bom:2023.09.02")
    val compose_version = "1.5.3"

    implementation(composeBoM)
    androidTestImplementation(composeBoM)
    implementation ("androidx.compose.material3:material3-android:1.2.0-beta01")
    implementation ("androidx.compose.ui:ui:$compose_version")
    implementation ("androidx.compose.ui:ui-tooling:$compose_version")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation ("androidx.compose.material:material-icons-extended:$compose_version")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}