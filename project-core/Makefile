#!make
include .env

setUp:
	@mvn clean install -DskipTests

up:
	@make rm; make setUp

stop:
	@docker stop ${CONTAINER_NAME}

rm:
	@make stop && docker rm ${CONTAINER_NAME}

sh:
	@docker exec -it ${CONTAINER_NAME} /bin/sh

log:
	@docker exec -it ${CONTAINER_NAME} tail -f /var/log/app.log

logDocker:
	@docker logs -f ${CONTAINER_NAME}

kill:
	@docker exec -it ${CONTAINER_NAME} fuser -k ${EXPOSE_PORT}/tcp

sonar:
	@mvn sonar:sonar -Dsonar.host.url=http://localhost:7000
