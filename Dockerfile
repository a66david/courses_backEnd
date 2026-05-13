# Etapa 1: Construcción usando la imagen oficial de Maven y Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Empaquetamos el código y nos saltamos los tests para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Una versión de Java súper ligera)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el archivo compilado desde la carpeta target (típico de Maven)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]