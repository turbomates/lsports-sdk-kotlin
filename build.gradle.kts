import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm").version(deps.versions.kotlin)
    alias(deps.plugins.kotlin.serialization)
    alias(deps.plugins.detekt)
    alias(deps.plugins.nexus.release)
    `maven-publish`
    signing
}

group = "com.github.turbomates"
version = "0.4.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    api(deps.rabbitmq.amqp.client)
    api(deps.log4j.slf4j)
    api(deps.ktor.client.auth.jvm)
    api(deps.ktor.client.cio)
    api(deps.ktor.serialization)
    api(deps.ktor.client.serialization)

    testImplementation(deps.kotlin.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
        )
    }
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("PASSED", "STARTED", "FAILED", "SKIPPED")
    }
    doFirst {
        System.getProperties().forEach { (k, v) ->
            systemProperty(k.toString(), v.toString())
        }
    }
}

detekt {
    toolVersion = deps.versions.detekt.get()
    buildUponDefaultConfig = true
    ignoreFailures = false
    parallel = true
    allRules = false
    config = files("detekt.yml")
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
