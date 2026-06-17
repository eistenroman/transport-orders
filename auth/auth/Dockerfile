FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/auth-0.0.1.jar
COPY ${JAR_FILE} app_auth.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_auth.jar"]
