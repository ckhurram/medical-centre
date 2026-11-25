FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ms-notification-service/pom.xml ./ms-notification-service/pom.xml
COPY ms-notification-service/src ./ms-notification-service/src
RUN mvn -f ms-notification-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/ms-notification-service/target/ms-notification-service-1.0.0.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]