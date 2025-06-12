#!/bin/bash
cd backend-java || exit
./gradlew build
cd ..
cd backend-kotlin || exit
./mvnw clean package
cd ..
docker compose up -d