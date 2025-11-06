FROM eclipse-temurin:8-jdk

RUN mkdir /twilio
WORKDIR /twilio

COPY src ./src
COPY pom.xml .

RUN apt-get update && apt-get install maven -y
RUN mvn clean install -Dmaven.test.skip=true
