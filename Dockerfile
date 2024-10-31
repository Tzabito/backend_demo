# Usa una imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-11 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Usa una imagen base de JDK para ejecutar la aplicación
FROM openjdk:11-jre-slim

# Copia el archivo JAR de la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Especifica el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
