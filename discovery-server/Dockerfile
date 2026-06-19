FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/driver-0.0.1.jar
COPY ${JAR_FILE} app_driver.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app_discovery-server.jar"]
