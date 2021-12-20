#!make
include .env

setUp:
	@docker-compose build

start:
	docker-compose up --detach

run:
	@make start && \
	docker exec -it --detach ${CONTAINER_NAME} \
	catalina.sh run

up:
	@make rm; make setUp && make start && make run

debug:
	@docker exec -it --detach ${CONTAINER_NAME} \
    bash -s "export JPDA_ADDRESS=8002 && export JPDA_TRANSPORT=dt_socket && catalina.sh jpda start"

stop:
	@docker stop ${CONTAINER_NAME} ${MYSQL_CONTAINER_NAME}

rm:
	@make stop && docker rm ${CONTAINER_NAME} ${MYSQL_CONTAINER_NAME}

sh:
	@docker exec -it ${CONTAINER_NAME} /bin/sh

log:
	@docker exec -it ${CONTAINER_NAME} \
	tail -f /usr/local/tomcat/logs/uaa.log /usr/local/tomcat/logs/localhost_access_log.*.log

logDocker:
	@docker logs -f ${CONTAINER_NAME}

kill:
	@docker exec -it ${CONTAINER_NAME} catalina.sh stop
