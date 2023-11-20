FROM maven:3.9-amazoncorretto-21 AS build

WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]