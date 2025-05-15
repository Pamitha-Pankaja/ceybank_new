# ---------- build stage ----------
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---------- run stage ----
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/ceybank-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]

