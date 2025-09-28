plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.mgmt)
}

group = "com.example.product.application"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get().toInt())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":product:domain"))
    implementation(project(":shared"))
    implementation(libs.spring.boot.actuator)
    implementation(libs.spring.boot.data.r2dbc)
    implementation(libs.spring.boot.data.redis.reactive)
    implementation(libs.spring.boot.validation)
    implementation(libs.spring.boot.webflux)
    implementation(libs.flyway.core)
    implementation(libs.flyway.postgres)
    implementation(libs.spring.jdbc)
    implementation(libs.ulid)
    implementation(libs.mapstruct)
    compileOnly(libs.lombok)
    developmentOnly(libs.spring.boot.devtools)
    runtimeOnly(libs.postgres)
    runtimeOnly(libs.r2dbc.postgres)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstruct.processor)
    testImplementation(libs.bundles.spring.test)
    testRuntimeOnly(libs.spring.junit.platform.launcher)
}

dependencyManagement {
    imports {
//        mavenBom("io.sentry:sentry-bom:${libs.versions.sentry.get()}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.versions.spring.cloud.get()}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}

