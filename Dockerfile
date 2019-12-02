# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="mario-dacosta@hotmail.fr"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/spring-rest-docker-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-rest-docker.jar

# Run the jar file
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/hoteldb","-Dspring.data.redis.uri=redis://redis","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-rest-docker.jar"]