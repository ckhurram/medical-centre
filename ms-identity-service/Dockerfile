FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ms-identity-service/pom.xml ./ms-identity-service/pom.xml
COPY ms-identity-service/src ./ms-identity-service/src
RUN mvn -f ms-identity-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/ms-identity-service/target/ms-identity-service-1.0.0.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/app.jar"]