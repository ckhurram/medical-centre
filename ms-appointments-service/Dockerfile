FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ms-appointments-service/pom.xml ./ms-appointments-service/pom.xml
COPY ms-appointments-service/src ./ms-appointments-service/src
RUN mvn -f ms-appointments-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/ms-appointments-service/target/ms-appointments-service-1.0.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]