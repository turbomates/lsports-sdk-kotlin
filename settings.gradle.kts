rootProject.name = "lsports"

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("kotlin", "1.6.10")
            version("rabbitmq_amqp_client", "5.13.1")

            alias("rabbitmq_amqp_client").to("com.rabbitmq", "amqp-client").versionRef("rabbitmq_amqp_client")
        }
    }
}
