docker pull amazoncorretto:17
./mvnw clean package jib:dockerBuild -P prod -DskipTests
docker-compose up -d --force-recreate --build peppermint_user_api_service
docker logs peppermint_user_api_service -f --tail 100
