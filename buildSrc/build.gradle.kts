import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins { `kotlin-dsl` }

kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories { mavenCentral() }