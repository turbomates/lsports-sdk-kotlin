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

group = "com.turbomates"
version = "0.1.0-alpha"

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

    testImplementation(deps.kotlin.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "15"
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
        )
    }
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_15
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
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "kotlin-lsports-sdk"
            groupId = "com.turbomates"
            from(components["java"])
            pom {
                packaging = "jar"
                name.set("Kotlin LSports SDK")
                url.set("https://github.com/turbomates/lsports-sdk-kotlin")
                description.set("This library is a client that describes sport line delivery from LSports service")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                scm {
                    connection.set("scm:https://github.com/turbomates/lsports-sdk-kotlin.git")
                    developerConnection.set("scm:git@github.com:turbomates/lsports-sdk-kotlin.git")
                    url.set("https://github.com/turbomates/lsports-sdk-kotlin")
                }

                developers {
                    developer {
                        id.set("vesurbag")
                        name.set("Sergey Grabrusev")
                        email.set("gabrusevs@gmail.com")
                    }
                    developer {
                        id.set("AndreiBaly")
                        name.set("Andrew Krawchenko")
                        email.set("andrei.krawchenko@gmail.com")
                    }
                    developer {
                        id.set("JinKoro")
                        name.set("Dmitry Korotin")
                        email.set("dskorotin@gmail.com")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            val releasesUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
            credentials {
                username = project.properties["ossrhUsername"].toString()
                password = project.properties["ossrhPassword"].toString()
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

nexusStaging {
    serverUrl = "https://s01.oss.sonatype.org/service/local/"
}
