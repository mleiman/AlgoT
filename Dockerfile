FROM eclipse-temurin:25-jdk-alpine as builder
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar algoT.jar

ENTRYPOINT ["java" ,"-jar", "algoT.jar"]