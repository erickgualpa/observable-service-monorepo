#!/bin/bash
cd backend-kotlin || exit
./mvnw clean package
cd ..
docker compose up -d