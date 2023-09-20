## Metadata Indexer using Yaci Store Spring Boot Starter

This a simple example of a metadata indexer using [Yaci Store](https://github.com/bloxbean/yaci-store) Spring Boot Starter.
Using this indexer, you can index all metadata labels. You can also customize the indexer to index only specific metadata labels.

### Dependencies

Apart from standard spring boot dependencies, this project has following dependencies:

```xml
<dependency>
    <groupId>com.bloxbean.cardano</groupId>
    <artifactId>yaci-store-spring-boot-starter</artifactId>
    <version>0.0.12</version>
</dependency>
<dependency>
    <groupId>com.bloxbean.cardano</groupId>
    <artifactId>yaci-store-metadata-spring-boot-starter</artifactId>
    <version>0.0.12</version>
</dependency>
```
### Configuration

#### Mandatory Configuration in application.yml 
In application.yml, you need to provide the following **mandatory** flyway configuration.

```yaml
spring:
  flyway:
    locations: classpath:db/store/{vendor}
    out-of-order: true

apiPrefix: /api/v1
```
Other Yaci Store specific configuration properties can be configured in config/application.properties or in application.yml.

#### Some key configuration properties
**File:** ``config/application.properties``

1. **Configure a network**

Select a network from the list of public networks or provide your own custom network details.

```
store.cardano.host=preprod-node.world.dev.cardano.org
store.cardano.port=30000
store.cardano.protocol-magic=1
```

**Note:** For **Devnet** created using [Yaci DevKit](https://github.com/bloxbean/yaci-devkit), you can use the following configuration.

```
store.cardano.host=localhost
store.cardano.port=3001
store.cardano.protocol-magic=42
```

2. **Configure a database (By default it uses H2 database)**

Uncomment the following properties and provide your database details.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/yaci_indexer
spring.datasource.username=user
spring.datasource.password=password
```

3. **By default, Yaci store fetches blocks from genesis block. If you want to start from a specific block, you can configure it as follows:**

```
store.cardano.sync-start-slot=2738868
store.cardano.sync-start-blockhash=a5c72a0e74cf066873ae7741c488acaef32746e1c4ac3b0d49c4acf4add1a47c
```

**For custom network & non-genesis block as start block**

When you start the application from **a non-genesis block** on **a custom network**, Yaci Store needs to know the Shelley era start block detail. This is requried to calculate
the era information correctly. You can configure the Shelley era start block as follows if it's a custom network and you are starting from a
non-genesis block.
For public networks, Yaci Store automatically detects the following information.

```shell
store.cardano.shelley-start-blockhash= 
store.cardano.shelley-start-slot=
store.cardano.shelley-start-block= 
```

### Out-of-box API endpoints

**Swagger UI:**  http://localhost:9095/swagger-ui.html

Currently, Yaci Store Metadata Spring Boot Starter provides following API endpoints:

```
- /api/v1/txs/{txHash}/metadata : Get metadata for a transaction
```

But, you can add your own API endpoints by writing a controller class.

**More endpoints will be added to the default metadata store in the future.**

### Custom Storage

Yaci Store provides a default metadata storage implementation which stores all metadata labels in ``transaction_metadata`` table.
But you can override this default implementation and provide your own storage implementation.

**For example:**
If you only want to store a specific metadata label, you can extend the default storage implementation and filter out the labels you want to store.
Check ``CustomMetadataStorage`` class for an example which filters out all labels except ``"721"``.
To use this custom storage implementation, you need uncomment ``@Component`` annotation in ``CustomMetadataStorage`` class. This will override the default storage implementation.

### Custom Processor 
Ideally you don't need to have your own custom processor, but if you want to do some custom processing, you can listen to
both core or derive events and do your custom processing.
Please check ``CustomMetadataProcessor`` class for an example.

### Rollback Handling
If you are using default storage implementation or a custom storage by extending default Metadata storage,
Yaci Store will automatically handle rollbacks.

But if you have your own custom processor or different storage implementation using separate database/table,
you need to handle rollbacks in your custom processor by listening to ``RollbackEvent``.

### Build and Run

#### Prerequisites

Java 17

```
./mvnw clean package
java -jar target/metdata-indexer-0.0.1-SNAPSHOT.jar
```
