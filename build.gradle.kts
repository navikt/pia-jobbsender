val logbackVersion = "1.5.20"
val logstashLogbackEncoderVersion = "9.0"
val opentelemetryLogbackMdcVersion = "2.16.0-alpha"

plugins {
    kotlin("jvm") version "2.2.21"
    id("application")
}

group = "no.nav"
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:4.1.0")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashLogbackEncoderVersion")
    implementation("io.opentelemetry.instrumentation:opentelemetry-logback-mdc-1.0:$opentelemetryLogbackMdcVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.2.21")

    constraints {
        implementation("org.lz4:lz4-java") {
            modules {
                module("org.lz4:lz4-java") {
                    replacedBy("at.yawk.lz4:lz4-java", "Fork of the original unmaintained lz4-java library that fixes a CVE")
                }
            }
            version {
                require("1.8.1")
            }
            because(
                "Fikser CVE-2025-12183 - lz4-java 1.8.0 har sårbar versjon (transitive dependency fra kafka-clients:4.1.0)",
            )
        }
    }
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
