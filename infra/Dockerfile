#FROM openjdk:17-jdk-alpine
#WORKDIR /app
#COPY ../target/penpixel-1.0.0.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]