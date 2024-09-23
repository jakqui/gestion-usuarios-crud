# Etapa de construcción
FROM eclipse-temurin:17.0.8.1_1-jdk-jammy AS build
WORKDIR /app

# Copia mvnw y establece permisos
COPY mvnw .
RUN chmod +x mvnw && ls -l mvnw

# Si es necesario, convertir a formato Unix y usar bash
RUN apt-get update && apt-get install -y dos2unix && dos2unix mvnw

# Copia otros archivos necesarios
COPY .mvn .mvn
COPY pom.xml .

# Verifica y ejecuta mvnw con bash
RUN ls -l /app && bash ./mvnw dependency:go-offline

# Copia el resto del código fuente
COPY src src

# Realiza el build
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17.0.8.1_1-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]