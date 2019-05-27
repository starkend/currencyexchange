#Spring Boot currencyexchange app Dockerfile for running locally
FROM openjdk:latest

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/currencyexchange-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} currency-exchange.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/currency-exchange.jar"]

#Build Image
#docker build -t currency-exchange .

#Run Image locally to allow localhost mapping to connect to local Postgres
#docker run -p 5000:8080 --network="host" currency-exchange