import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.qameta.allure") version "2.11.2"
    kotlin("jvm") version "1.9.22"
}

group = "com.saucedemo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Allure configuration
allure {
    version.set("2.17.2")
    adapter {
        frameworks {
            junit5 {
                enabled.set(true)
            }
        }
    }
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib"))

    // Playwright for browser automation
    testImplementation("com.microsoft.playwright:playwright:1.19.0")

    // JUnit 5 for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    // Kotest for assertions (more fluent than standard JUnit)
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")

    // SLF4J for logging API
    testImplementation("org.slf4j:slf4j-api:2.0.12")

    // Logback for logging implementation
    testImplementation("ch.qos.logback:logback-classic:1.5.3")
    
    // Allure integration
    testImplementation("io.qameta.allure:allure-junit5:2.17.2")
    testImplementation("io.github.uchagani:allure-playwright-java:1.1.0")

    // Properties reader
    testImplementation("commons-configuration:commons-configuration:1.10")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }
    systemProperties.put("allure.results.directory", project.buildDir.resolve("allure-results"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
