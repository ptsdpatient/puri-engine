plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "android-gradlew-test"

include("core")
include("desktop")
include("app")
