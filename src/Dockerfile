# Etapa 1: Construcción
FROM gradle:8-jdk17 AS build
WORKDIR /app
COPY . .
# Le damos permisos al constructor y empaquetamos tu código
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el archivo compilado de la etapa 1
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]