FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/order-0.0.1.jar
COPY ${JAR_FILE} app_order.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app_order.jar"]
