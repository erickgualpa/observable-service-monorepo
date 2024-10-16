FROM bellsoft/liberica-openjdk-alpine-musl:21
COPY target/observable-wallet-interaction-service-0.0.1-SNAPSHOT.jar observable-wallet-interaction-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "observable-wallet-interaction-service-0.0.1-SNAPSHOT.jar"]