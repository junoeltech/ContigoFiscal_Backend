# Paso 1: Construir la aplicación (Usando JDK 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar la aplicación
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Copiamos el jar generado. El asterisco ayuda si el nombre tiene versiones
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]