plugins {
    kotlin("jvm") version "2.1.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "no.nav"
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:4.0.0")
    implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("net.logstash.logback:logstash-logback-encoder:8.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.1.20")
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

tasks {
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "MainKt"))
        }
    }

    withType<Test> {
        dependsOn(shadowJar)
    }
}
