# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Run stage
FROM openjdk:8-jre-alpine
EXPOSE 8080
WORKDIR /usr/app
COPY --from=build /app/target/java-maven-app-*.jar /usr/app/
CMD java -jar java-maven-app-*.jar