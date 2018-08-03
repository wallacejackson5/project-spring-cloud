build:
	@mvn clean install -DskipTests && sudo docker build -t project-feign-service .

run:
	@sudo docker-compose up -d

stop:
	@sudo docker stop project-feign-service

sh:
	@sudo docker exec -it project-feign-service /bin/sh

log:
	@sudo docker logs -f project-feign-service
