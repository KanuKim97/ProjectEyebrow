plugins {
    id(Plugins.android_application)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_ksp)
    id(Plugins.dagger_hilt)
    id(Plugins.google_gms_service)
}

kotlin { jvmToolchain(ApplicationConfig.jdkVersion) }

android {
    namespace = "com.example.projecteyebrow"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.projecteyebrow"
        minSdk = ApplicationConfig.minSdk
        targetSdk = ApplicationConfig.targetSdk
        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures { compose = true }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    composeOptions { kotlinCompilerExtensionVersion = Versions.compose }
}

dependencies {
    implementation (Libraries.android_core)

    implementation(project(Module.domain))
    implementation(project(Module.data))
    implementation(project(Module.ui))
    implementation(project(Module.designsystem))

    /* -- Android Test Dependencies -- */
    testImplementation (Libraries.jUnit)
    androidTestImplementation (Libraries.androidX_Junit)
    androidTestImplementation (Libraries.androidX_EspressoCore)

    /* -- FireBase -- */
    val firebaseBoM = platform(Libraries.firebaseBoM)

    implementation (firebaseBoM)
    implementation (Libraries.firebase_Auth)
    implementation (Libraries.firebase_UI_Auth)
    implementation (Libraries.firebase_Analytics)
    implementation (Libraries.firebase_Database)
    implementation (Libraries.firebase_Storage)
    implementation (Libraries.fireStore)
    implementation (Libraries.gms_play_service_Auth)

    /* -- Kotlin Coroutines -- */
    implementation (Libraries.kotlin_coroutine)

    /* -- Room Database -- */
    implementation (Libraries.room_Runtime)
    ksp (Libraries.room_Compiler)
    implementation (Libraries.room_ktx)

    /* -- Dagger-Hilt DI(Dependency Injection) Tool-- */
    implementation (Libraries.hilt_Android)
    ksp (Libraries.hilt_Android_Compiler)

    /* -- Landscapist Glide ImageLoader (SkyDoves) -- */
    implementation (Libraries.LandScapist_Glide)

    /*-- Jetpack Compose --*/
    val composeBoM = platform(Libraries.compose_BoM)

    implementation(composeBoM)
    androidTestImplementation(composeBoM)
    implementation (Libraries.compose_Material3)
    implementation (Libraries.compose_Navigation)
    implementation (Libraries.compose_Hilt_Navigation)
    implementation (Libraries.compose_ViewModel)

    implementation (Libraries.compose_UI)
    implementation (Libraries.compose_UI_Tooling)
    implementation (Libraries.compose_UI_Preview)
    implementation (Libraries.compose_LiveData)
    implementation (Libraries.compose_Material_Icon)
}