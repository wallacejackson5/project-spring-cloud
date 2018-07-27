buildProject:
	@mvn clean install -DskipTests

build:
	@docker build -t project-order-service .

run:
	@docker-compose up -d

stop:
	@docker stop project-order-service

sh:
	@docker exec -it project-order-service /bin/sh

log:
	@docker logs -f project-order-service
