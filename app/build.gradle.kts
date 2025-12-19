plugins {
    id("com.android.application")
}

android {
    namespace = "com.puri.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.puri.app"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(8)
        targetCompatibility = JavaVersion.toVersion(8)
    }
}

dependencies {
    // no dependencies yet
}
