FROM bellsoft/liberica-openjdk-alpine-musl:21
COPY backend/target/wallet-interaction-service-0.0.1-SNAPSHOT.jar wallet-interaction-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "wallet-interaction-service-0.0.1-SNAPSHOT.jar"]