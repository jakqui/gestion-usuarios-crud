# Etapa de construcción
FROM eclipse-temurin:17.0.8.1_1-jdk-jammy AS build
WORKDIR /app

# Copia solo los archivos necesarios para la descarga de dependencias
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Descarga las dependencias
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copia el resto del código fuente
COPY src src

# Realiza el build
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17.0.8.1_1-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]