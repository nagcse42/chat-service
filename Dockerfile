# OpenJDK  image 
FROM openjdk:17-jdk-slim

# Set the working directory to /app 
WORKDIR /app 
# Copy the current directory contents into the container at /app 
COPY target/*.jar /app/chat-service.jar

# Make port 8080 available to the world outside this container 
EXPOSE 8080 

# Run your application using the JAR file 
CMD ["java", "-jar", "chat-service.jar"]