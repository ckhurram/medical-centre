FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ms-patients-service/pom.xml ./ms-patients-service/pom.xml
COPY ms-patients-service/src ./ms-patients-service/src
RUN mvn -f ms-patients-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/ms-patients-service/target/ms-patients-service-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]