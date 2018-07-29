build:
	@mvn clean install -DskipTests && sudo docker build -t project-eureka-server .

run:
	@sudo docker-compose up -d

stop:
	@sudo docker stop project-eureka-server

sh:
	@sudo docker exec -it project-eureka-server /bin/sh

log:
	@sudo docker logs -f project-eureka-server
