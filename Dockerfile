# Build and run Java 21 application
FROM maven:3.9.9-eclipse-temurin-21 AS build-and-run

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application
COPY src ./src
RUN mvn clean package

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/target/website-1.0.jar"]
