#!/bin/bash
cd backend || exit
./mvnw clean package
cd ..
docker compose up -d