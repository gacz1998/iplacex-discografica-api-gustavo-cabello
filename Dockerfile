# ETAPA 1: BUILD
# Usa una imagen de Gradle con JDK 22 para la construcción.
FROM gradle:8.7-jdk22 AS builder
WORKDIR /app
COPY . .
# Asegura que el script de Gradle sea ejecutable.
RUN chmod +x ./gradlew
# Ejecuta la construcción del proyecto y omite las pruebas.
RUN ./gradlew clean build -x test --no-daemon

# ETAPA 2: RUN
# Usa una imagen base ligera con OpenJDK 22 para la ejecución.
FROM openjdk:22-jdk-slim
WORKDIR /app
# Copia el archivo .jar de la etapa de construcción.
COPY --from=builder /app/build/libs/*.jar ./app.jar
# Comando para ejecutar la aplicación.
CMD ["java", "-jar", "app.jar"]
