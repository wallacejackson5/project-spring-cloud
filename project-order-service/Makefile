buildProject:
	@mvn clean install -DskipTests

build:
	@sudo docker build -t project-order-service .

run:
	@sudo docker-compose up -d

stop:
	@sudo docker stop project-order-service

sh:
	@sudo docker exec -it project-order-service /bin/sh

log:
	@sudo docker logs -f project-order-service
