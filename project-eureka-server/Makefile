build:
	@mvn clean install -DskipTests && docker build -t project-eureka-server .

run:
	@docker-compose up -d

stop:
	@docker stop project-eureka-server

sh:
	@docker exec -it project-eureka-server /bin/sh

log:
	@docker logs -f project-eureka-server
