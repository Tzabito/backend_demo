# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Define el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/greentools-web-service.jar greentools-web-service.jar

# Expone el puerto en el que tu aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "greentools-web-service.jar"]