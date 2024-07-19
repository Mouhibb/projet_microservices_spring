# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application's jar file into the container at /app
COPY target/GatewayApi*.jar /app/app.jar

# Make port 8089 available to the world outside this container
EXPOSE 8089

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
