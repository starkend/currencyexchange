# currencyexchange
Proof of Concept MVC App built with Spring Boot 2.x, Angular 7, and Postgres 9.x

The business context of this app is foreign currency exchange.  A currency trading pair, such as USD & AUD, are referred to as a Symbol.  The external APIs consumed in this app can be found at 1Forge Rest APIs: https://1forge.com/forex-data-api/api-documentation.

Functionality:
-Spring Boot
  -MVC Architecture
  -Consumes 1Forge Rest APIs
  -Serves Rest Endpoints(Get and Post)
  -
  
-Angular
  -Display Angular Material data table of Currency Symbol Pairs
  -Created Angular components and service to consume Rest endpoints served by Spring Boot
  -Save Angular model object from UI to Postgres via Http Post call to Spring Boot endpoint
-Create/Read/Update/Delete to Postgres database
-
-


