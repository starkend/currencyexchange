# currencyexchange
Proof of Concept MVC App built with **Spring Boot 2.x, Angular 7, and Postgres 9.x**

The business context of this app is foreign currency exchange.  A currency trading pair, such as USD & AUD, are referred to as a Symbol.  The external APIs consumed in this app can be found at 1Forge Rest APIs: https://1forge.com/forex-data-api/api-documentation.

**Functionality:**
- Spring Boot
  - MVC Architecture
  - Established with Spring Initializer
  - Consumes 1Forge Rest APIs
  - Serves Rest Endpoints(Get and Post)
  - Unit and Integration Tests
  
- Angular
  - Established with Angular CLI
  - Display Angular Material data tables of Currency Symbol Pairs
    - One table is populated from a 1Forge Symbol Trading Pairs API
    - Another table is populated from a Postgres table containing saved Symbol Trading Pairs
  - Created Angular components and service to consume Rest endpoints served by Spring Boot
  - Save Angular model object from UI to Postgres via Http Post call to Spring Boot endpoint

- Postgres
  - Established by creating a local Postgres instance
  - Create/Read/Update/Delete functionality via Spring Repository
