val logbackVersion = "1.5.32"
val logstashLogbackEncoderVersion = "9.0"
val opentelemetryLogbackMdcVersion = "2.26.0-alpha"

plugins {
    kotlin("jvm") version "2.3.10"
    id("application")
}

group = "no.nav"
repositories {
    mavenCentral()
}

dependencies {
    // Kafka
    implementation("at.yawk.lz4:lz4-java:1.10.4")
    implementation("org.apache.kafka:kafka-clients:4.2.0") {
        // "Fikser CVE-2025-12183 - lz4-java >1.8.1 har sårbar versjon (transitive dependency fra kafka-clients:4.1.0)"
        exclude("org.lz4", "lz4-java")
    }
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashLogbackEncoderVersion")
    implementation("io.opentelemetry.instrumentation:opentelemetry-logback-mdc-1.0:$opentelemetryLogbackMdcVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.3.10")

    constraints {
        implementation("tools.jackson.core:jackson-core") {
            version { require("3.1.0") }
            because("versjoner < 3.1.0 har sårbarhet. inkludert i logstash-logback-encoder:9.0")
        }
    }
}

tasks {
    test {
        dependsOn(installDist)
        useJUnitPlatform()
    }
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("MainKt")
}
