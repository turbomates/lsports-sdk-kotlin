# Kotlin SDK for LSports

LSports SDK is a client that provides real-time sports data solution.
This client ability to:
- get continuous delivery by the `RabbitMQ` connection 
- get in-play and pre-match data by `LSports API`
- transform `LSports` message to a convenient responses

## Configuration LSports Client

- Sing in [LSports Client](https://client.lsports.eu/)
- Get packageIds and guid from `LSports`
- Add your local IP at the [Account Settings](https://client.lsports.eu/OddService/Account)
- Start Distribution in the `Json` format [Distributor](https://client.lsports.eu/OddService/Pusher)

## Using LSports SDK

- `Add LSports client to your dependencies`: 
```
Gradle build.gradle.kts:
 
    dependencies {
        // Kotlin DSL
        implementation("com.turbomates:kotlin-lsports-sdk:0.0.1-alpha")
        // Groovy
        implementation 'com.turbomates:kotlin-lsports-sdk:0.0.1-alpha'
    }


Maven pom.xml: 

    <dependency>
        <groupId>com.turbomates</groupId>
        <artifactId>kotlin-lsports-sdk</artifactId>
        <version>0.0.1-alpha</version>
    </dependency>
```

- `Start using client`: 
```
    val client = LSportsClient {
        username = "<your_lsports_client_username>"
        password = "<your_lsports_client_password>"
        inPlayPackageId = "1111"
        preMatchPackageId = "2222"
        guid = "42a23af9-8179-420e-bb6c-f07d31ca1782"
    }

    val handler = InPlayHandler()
      
    client.inPlay.listen(handler)   // RabbitMQ listening
    client.inPlay.api.schedule()    // API by HTTP request
    client.inPlay.api.schedule {
        sportsIds = listOf("4206")  // or add filters
    }   
```
- `Implement Handler interfaces to get a different types of the messages and set the desired behavior`:
``` 
    class InPlayHandler() : Handler {
        override fun handle(message: Message) {
            println(message)
        }
    }
```