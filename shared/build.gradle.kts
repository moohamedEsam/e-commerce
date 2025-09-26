plugins {
    java
}

group = "com.example.shared"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get().toInt())
    }
}

dependencies {
    implementation(libs.ulid)
    implementation(libs.reactor)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.test {
    useJUnitPlatform()
}