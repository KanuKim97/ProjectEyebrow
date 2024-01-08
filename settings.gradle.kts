pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Project Eyebrow"
include (":app")

/* feature module */
include (":feature:home")
include (":feature:logIn")
include (":feature:tempContent")
include (":feature:findPWD")
include (":feature:collection")

/* core module */
include (":core:common")
include (":core:data")
include (":core:database")
include (":core:designsystem")
include (":core:domain")
include (":core:model")
include (":core:network")
include (":core:ui")