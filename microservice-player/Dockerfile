# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application's jar file into the container at /app
COPY target/Player*.jar /app/app.jar

# Make port 9090 available to the world outside this container
EXPOSE 9090

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
