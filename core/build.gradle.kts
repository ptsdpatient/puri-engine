plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    // Math utilities (optional but useful for games)
    // implementation("org.joml:joml:1.10.5")

    // // Logging (optional)
    // implementation("org.slf4j:slf4j-api:2.0.9")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
