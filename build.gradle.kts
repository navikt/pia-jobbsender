plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "no.nav"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:3.6.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}