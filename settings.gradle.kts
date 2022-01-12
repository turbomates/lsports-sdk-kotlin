rootProject.name = "com.turbomates.kotlin.lsports-sdk"
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("log4j", "2.15.0")
            version("kotlin", "1.6.10")
            version("detekt", "1.19.0")
            version("rabbitmq_amqp_client", "5.14.0")

            alias("log4j_slf4j").to("org.apache.logging.log4j", "log4j-slf4j-impl").versionRef("log4j")
            alias("rabbitmq_amqp_client").to("com.rabbitmq", "amqp-client").versionRef("rabbitmq_amqp_client")

            alias("detekt").toPluginId("io.gitlab.arturbosch.detekt").versionRef("detekt")
        }
    }
}
