#!make
include .env

setUp:
	@mvn compile jib:dockerBuild

run:
	@docker run -p ${EXPOSE_PORT}:${EXPOSE_PORT} -d --network ${NETWORK_NAME} \
		-e JAVA_TOOL_OPTIONS="-Dspring.profiles.active=${PROFILE}" --name ${CONTAINER_NAME} ${IMAGE_NAME};

up:
	@make rm; make setUp && make run

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
