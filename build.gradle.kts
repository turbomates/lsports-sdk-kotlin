import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm").version(deps.versions.kotlin)
    alias(deps.plugins.kotlin.serialization)
    alias(deps.plugins.detekt)
}

group = "com.turbomates.kotlin.lsports-sdk"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    api(deps.rabbitmq.amqp.client)
    api(deps.log4j.slf4j)
    api(deps.ktor.client.auth.jvm)
    api(deps.ktor.client.cio)
    api(deps.ktor.serialization)
    api(deps.ktor.client.serialization)
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")

    testImplementation(deps.kotlin.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "15"
    }
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_15
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("PASSED", "STARTED", "FAILED", "SKIPPED")
        // showStandardStreams = true
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
