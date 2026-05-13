# Etapa 1: Construcción con Gradle
FROM gradle:8-jdk17 AS build
WORKDIR /app
COPY . .
# Usamos gradle directo para evitar problemas de permisos en Windows
RUN gradle build -x test

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]