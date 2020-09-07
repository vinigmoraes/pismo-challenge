import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("application")
}

version = "unspecified"

application {
    mainClassName = "br.com.pismochallenge.application.PismoChallengeApplicationKt"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.koin:koin-ktor:2.0.1")
    implementation("io.ktor:ktor-jackson:1.2.6")
    implementation("io.ktor:ktor-server-netty:1.2.6")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.2")
    implementation("org.jetbrains.exposed", "exposed-core", "0.22.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.22.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.22.1")
    implementation("org.jetbrains.exposed", "exposed-java-time", "0.22.1")
    implementation("org.postgresql:postgresql:42.2.8")
    implementation("com.zaxxer:HikariCP:3.4.1")
    implementation("org.flywaydb:flyway-core:6.1.0")
    implementation("com.github.kittinunf.fuel:fuel:2.2.0")

    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("org.testcontainers:postgresql:1.12.3")
    testImplementation("io.ktor:ktor-server-test-host:1.3.1")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("junit:junit:4.13")

    api("ch.qos.logback:logback-classic:1.2.3")
}

tasks {

    assemble { dependsOn(shadowJar) }

    create("unitTest", Test::class) {
        filter {
            includeTestsMatching("br.com.pismochallenge.core.*")
        }
        testLogging {
            events(TestLogEvent.PASSED)
            events(TestLogEvent.FAILED)
        }
    }

    create("integrationTest", Test::class) {
        filter {
            includeTestsMatching("integration.*")
        }
        testLogging {
            events(TestLogEvent.PASSED)
            events(TestLogEvent.FAILED)
        }
    }

    shadowJar {
        manifest {
            attributes(mapOf("Main-Class" to "io.ktor.server.netty.EngineMain"))
        }
        destinationDirectory.dir("./postman")
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
