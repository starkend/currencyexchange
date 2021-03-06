# currencyexchange
Proof of Concept MVC App built with **Spring Boot 2.x, Angular 7, and Postgres 9.x. The app currently runs on Java 14**

The business context of this app is foreign currency exchange.  A currency trading pair, such as USD & AUD, are referred to as a Symbol.  The external APIs consumed in this app are provided by the European Central Bank(ECB) at https://exchangeratesapi.io/ and from Coinbase at https://developers.coinbase.com/api/v2.

This app has also been successfully deployed to AWS.  It runs on an EC2 instance with the Angular content served via NGINX and the Spring Boot portion via an executable jar. PostgreSQL is deployed to an RDS instance.

The Spring Boot and Angular portions have both been successfully run locally in Docker images.

**Note:** This app currently requires a running instance of Elasticsearch for all Tests to run successfully.
Current Elasticsearch version in the app is 7.6.2
docker run -p 9200:9200 -p 9300:9300 -v $PWD/elasticsearch/data:/usr/share/elasticsearch/data -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.6.2

### Functionality
- **Spring Boot**
  - MVC Architecture
  - Established with Spring Initializer
  - Consumes ECB Rest APIs
  - Serves Rest Endpoints(Get and Post)
  - Unit and Integration Tests


- **Angular**
  - Established with Angular CLI
  - Display Angular Material data tables of Currency Symbol Pairs
    - One table is populated from an ECB Symbol API
    - Another table is populated from a Postgres table containing saved Symbol Trading Pairs
  - Created Angular components and service to consume Rest endpoints served by Spring Boot
  - Save Angular model object from UI to Postgres via Http Post call to Spring Boot endpoint


- **Postgres**
  - Established by creating a local Postgres instance
  - Create/Read/Update/Delete functionality via Spring Repository
