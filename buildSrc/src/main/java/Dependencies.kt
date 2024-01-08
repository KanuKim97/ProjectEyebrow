object Versions {
    const val gradleVersion: String = "8.2.0"
    const val kotlin: String = "1.9.10"
    const val ksp: String = "1.9.10-1.0.13"
    const val hilt: String = "2.49"
    const val gms: String = "4.4.0"

    const val compose: String = "1.5.3"
    const val composeBoM: String = "2023.09.02"
    const val composeViewModel: String = "2.6.2"
    const val composeNavigation: String = "2.7.6"
    const val composeHiltNavigation: String = "1.1.0"
    const val composeMaterial3: String = "1.2.0-beta01"

    const val androidxCore: String = "1.12.0"

    const val jUnit: String = "4.13.2"
    const val androidxJunit: String = "1.1.5"
    const val espressoCore: String = "3.5.1"

    const val coroutine: String = "1.7.3"

    const val firebaseBoM: String = "32.3.1"
    const val firebaseAuth: String = "22.3.0"
    const val firebaseUiAuth: String = "8.0.2"
    const val firebaseDataBase: String = "20.3.0"
    const val firebaseStorage: String = "20.3.0"
    const val fireStore: String = "24.10.0"
    const val gmsPlayService: String = "20.7.0"

    const val room: String = "2.6.1"

    const val landScapistGlide = "2.2.10"
}

object ProjectDependencies {
    const val google_gms_services = "com.google.gms:google-services:${Versions.gms}"
}

object Plugins {
    const val android_application = "com.android.application"
    const val android_library = "com.android.library"
    const val kotlin_android = "org.jetbrains.kotlin.android"
    const val dagger_hilt = "com.google.dagger.hilt.android"
    const val kotlin_ksp = "com.google.devtools.ksp"
    const val kotlin_jvm = "org.jetbrains.kotlin.jvm"
    const val java_library = "java-library"
    const val google_gms_service = "com.google.gms.google-services"
}

object Libraries {
    // AndroidX Core
    const val android_core = "androidx.core:core-ktx:${Versions.androidxCore}"

    // Test
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidX_Junit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val androidX_EspressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    // Compose Libraries
    const val compose_BoM = "androidx.compose:compose-bom:${Versions.composeBoM}"
    const val compose_UI = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_UI_Tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val compose_UI_Preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val compose_LiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val compose_Material_Icon = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val compose_Navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val compose_Hilt_Navigation = "androidx.hilt:hilt-navigation-compose:${Versions.composeHiltNavigation}"
    const val compose_ViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val compose_Material3 = "androidx.compose.material3:material3-android:${Versions.composeMaterial3}"

    // LandScapist Glide
    const val LandScapist_Glide = "com.github.skydoves:landscapist-glide:${Versions.landScapistGlide}"

    // Firebase Libraries
    const val firebaseBoM = "com.google.firebase:firebase-bom:${Versions.firebaseBoM}"
    const val firebase_Auth = "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuth}"
    const val firebase_UI_Auth = "com.firebaseui:firebase-ui-auth:${Versions.firebaseUiAuth}"
    const val firebase_Analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebase_Database = "com.google.firebase:firebase-database:${Versions.firebaseDataBase}"
    const val firebase_Storage = "com.google.firebase:firebase-storage-ktx:${Versions.firebaseStorage}"
    const val fireStore = "com.google.firebase:firebase-firestore-ktx:${Versions.fireStore}"
    const val gms_play_service_Auth = "com.google.android.gms:play-services-auth:${Versions.gmsPlayService}"


    // Coroutine
    const val kotlin_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    // Dagger-Hilt
    const val hilt_Android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_Android_Compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    // Room
    const val room_Runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_Compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Module {
    // App Module
    const val app = ":app"

    // Core Module
    const val common = ":core:common"
    const val data = ":core:data"
    const val database = ":core:database"
    const val designsystem = ":core:designsystem"
    const val domain = ":core:domain"
    const val model = ":core:model"
    const val network = ":core:network"
    const val ui = ":core:ui"

    // Feature Module
    const val collection = ":feature:collection"
    const val findPWD = ":feature:findPWD"
    const val home = ":feature:home"
    const val logIn = ":feature:logIn"
    const val tempContent = ":feature:tempContent"
}