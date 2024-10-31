# Etapa de construcción
FROM maven:3.9.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /app/target/greentools-web-service.jar greentools-web-service.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "greentools-web-service.jar"]
