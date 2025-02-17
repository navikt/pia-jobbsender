plugins {
    kotlin("jvm") version "2.1.10"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "no.nav"
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:3.9.0")
    implementation("ch.qos.logback:logback-classic:1.5.16")
    implementation("net.logstash.logback:logstash-logback-encoder:8.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.1.10")
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
