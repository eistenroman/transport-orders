FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/assignment-0.0.1.jar
COPY ${JAR_FILE} app_assignment.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app_assignment.jar"]
