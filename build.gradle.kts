val logbackVersion = "1.5.18"
val logstashLogbackEncoderVersion = "8.1"
val opentelemetryLogbackMdcVersion = "2.16.0-alpha"

plugins {
    kotlin("jvm") version "2.2.10"
    id("application")
}

group = "no.nav"
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:4.0.0")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashLogbackEncoderVersion")
    implementation("io.opentelemetry.instrumentation:opentelemetry-logback-mdc-1.0:$opentelemetryLogbackMdcVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.2.10")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}
