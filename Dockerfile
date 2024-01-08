FROM openjdk:17-jdk-slim
WORKDIR /app
COPY  mvnw  mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml
COPY src src
RUN ./mvnw install -DskipTests
COPY target/*.jar target/app.jar
# Expose the port on which your application will run
EXPOSE 8080
CMD ["java", "-jar", "target/app.jar"]