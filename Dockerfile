# Twilio platform build image (JDK 17 + Maven) with the eMudhra (CVE-2026-111943) JVM trust fix, build 959.
FROM registry.twilio.com/library/java/build-maven-jdk-17-corretto:17.0.20-20261021-959@sha256:2194ec80cfc10cba1b031de0aebec43fc71c1e30557f3c9d2cabe44dca154057

RUN mkdir /twilio
WORKDIR /twilio
# The image has no locale set; javac needs UTF-8 to read the sources.
ENV LANG=C.UTF-8

COPY src ./src
COPY pom.xml .

RUN mvn clean install -Dmaven.test.skip=true
