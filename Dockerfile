
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY target/greentools-web-service.jar greentools-web-service.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "greentools-web-service.jar"]
