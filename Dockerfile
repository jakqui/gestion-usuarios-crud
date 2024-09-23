# Etapa de compilación
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar msgu.jar
CMD ["java", "-jar", "msgu.jar"]