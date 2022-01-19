rootProject.name = "com.turbomates.kotlin.lsports-sdk"
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("ktor", "1.6.4")
            version("log4j", "2.15.0")
            version("kotlin", "1.6.10")
            version("detekt", "1.19.0")
            version("rabbitmq_amqp_client", "5.14.0")

            alias("log4j_slf4j").to("org.apache.logging.log4j", "log4j-slf4j-impl").versionRef("log4j")
            alias("rabbitmq_amqp_client").to("com.rabbitmq", "amqp-client").versionRef("rabbitmq_amqp_client")
            alias("ktor_client_serialization").to("io.ktor", "ktor-client-serialization").versionRef("ktor")
            alias("ktor_client_auth_jvm").to("io.ktor", "ktor-client-auth-jvm").versionRef("ktor")
            alias("ktor_serialization").to("io.ktor", "ktor-serialization").versionRef("ktor")
            alias("ktor_client_cio").to("io.ktor", "ktor-client-cio").versionRef("ktor")

            alias("detekt").toPluginId("io.gitlab.arturbosch.detekt").versionRef("detekt")
            alias("kotlin_serialization").toPluginId("org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
        }
    }
}
