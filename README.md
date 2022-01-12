# Kotlin SDK for LSports

# Configuration

Create ```local.properties``` in the resources of src/main and put there the following settings

```
lsport.sdk.rabbit.port = <port>
lsport.sdk.rabbit.host.preLive = <host_preLive>
lsport.sdk.rabbit.host.live = <host_live>
lsport.sdk.rabbit.username = <username>
lsport.sdk.rabbit.password = <password>
lsport.sdk.rabbit.virtualHost = <virtual_host>
lsport.sdk.rabbit.queue.preLive = <queue_preLive>
lsport.sdk.rabbit.queue.live = <queue_live>
lsport.sdk.rabbit.autoRecovery = <autoRecovery>
lsport.sdk.rabbit.networkRecovery = <networkRecovery>
lsport.sdk.rabbit.heartbeat = <heartbeat>
```

- #### [Setup connection to LSport]("https://lsports.api-docs.io/v4/connection-and-consumption")

#### Format code

```bash
./gradlew detekt
```

- #### [Setup Ide Code style](https://github.com/pinterest/ktlint#option-3)
