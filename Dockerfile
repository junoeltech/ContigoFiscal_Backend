# Paso 1: Construir la aplicación
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

# Entramos a la carpeta donde está el pom.xml realmente
WORKDIR /app/contigo_fiscal
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar la aplicación
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Buscamos el jar dentro de la subcarpeta target
COPY --from=build /app/contigo_fiscal/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]