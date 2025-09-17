FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/build/libs/iplacex-discografica-api-1.0.jar ./app.jar
CMD ["java", "-jar", "app.jar"]