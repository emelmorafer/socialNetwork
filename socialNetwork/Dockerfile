FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/socialNetwork-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_socialNetwork.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_socialNetwork.jar"]