FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ms-medics-service/pom.xml ./ms-medics-service/pom.xml
COPY ms-medics-service/src ./ms-medics-service/src
RUN mvn -f ms-medics-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/ms-medics-service/target/ms-medics-service-1.0.0.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]