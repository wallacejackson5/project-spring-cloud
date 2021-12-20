#!make
include .env

setUp:
	@mkdir -p ~/.m2/repository; \
	docker network create ${NETWORK_NAME}; \
	docker pull wallacejackson5/java-machine:jdk11-0.0.1; \
	docker pull wallacejackson5/elk-machine:0.0.1; \
	docker pull wallacejackson5/sonar-machine:0.0.1; \
	docker pull tomcat:9.0.21-jdk8-adoptopenjdk-hotspot; \
	docker pull redis:6.2.3-alpine3.13; \
	docker run --network ${NETWORK_NAME} --name sonar-machine --publish 7000:7000 --detach wallacejackson5/sonar-machine:0.0.1; \
	docker run --network ${NETWORK_NAME} --name redis-machine --publish 7001:6379 --detach redis:6.2.3-alpine3.13; \
	docker run --network ${NETWORK_NAME} --name elk-machine --publish 7005:7005 --publish 7003:7003 --detach wallacejackson5/elk-machine:0.0.1; \
	mvn clean;
	cd project-config-server && make setUp; \
	cd ../project-eureka-server && make setUp; \
	cd ../project-sso-server && make setUp; \
	cd ../project-gateway-server && make setUp; \
	cd ../project-core && make setUp; \
	cd ../project-tests && make setUp; \
	cd ../project-resources && make setUp;

run:
	@cd ./project-config-server && make run && sleep 10 && \
	cd ../project-eureka-server && make run && sleep 5 && \
    cd ../project-sso-server && make run && sleep 5 && \
	cd ../project-gateway-server && make run && sleep 5 && \
	cd ../project-tests && make run && \
	cd ../project-resources && make run

up:
	@make rm; make setUp && make run

stop:
	@docker ps -a -q --filter=name='project-*:*' | xargs docker stop; \
	docker stop sonar-machine; docker stop elk-machine; docker stop redis-machine

rm:
	@make stop && \
	make dockerRmContainers; docker rm sonar-machine; docker rm elk-machine; docker rm redis-machine; \
	docker network prune -f; docker volume prune -f

build:
	@docker exec -it ${CONTAINER_NAME} \
    mvn clean install

dockerRmImages:
	@docker images -a --filter=reference='project-*:*' --format "{{.ID}}" | xargs docker rmi -f
	
dockerClean:
	@make stop && make rm && make dockerRmImages

dockerRmContainers:
	@docker ps -a -q | xargs docker stop && docker ps -a -q | xargs docker rm

dockerRmNetworks:
	@docker network prune -f

dockerRmVolumes:
	@docker volume prune -f

dockerRmImages_all:
	@docker images -a --format "{{.ID}}" | xargs docker rmi -f

dockerClean_all:
	@make dockerRmContainers; make dockerRmNetworks; make dockerRmImages_all; make dockerRmVolumes

startAdminer:
	docker network create ${NETWORK_NAME}; docker stop ${ADMINER_CONTAINER_NAME}; docker rm ${ADMINER_CONTAINER_NAME}; \
	docker create --env-file .env --name ${ADMINER_CONTAINER_NAME} --network ${NETWORK_NAME} -p ${ADMINER_EXPOSE_PORT}:8080 ${ADMINER_IMAGE_NAME} && \
	docker start ${ADMINER_CONTAINER_NAME}