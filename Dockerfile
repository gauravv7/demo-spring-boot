# Stage 1: Build Stage
FROM openjdk:8-jdk-alpine as build

WORKDIR /app

COPY gradlew gradlew.bat /app/
COPY gradle /app/gradle
COPY . /app/

RUN chmod +x gradlew
RUN ./gradlew build -x test

# Stage 2: Runtime Stage
FROM openjdk:8-jre-alpine

WORKDIR /app

# Copy only the built jar from the build stage
COPY --from=build /app/build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

