FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ms-api-gateway/pom.xml ./ms-api-gateway/pom.xml
COPY ms-api-gateway/src ./ms-api-gateway/src
RUN mvn -f ms-api-gateway/pom.xml clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/ms-api-gateway/target/ms-api-gateway-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]