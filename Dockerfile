FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/your-app.jar /app/your-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/your-app.jar"]
