buildProject:
	@mvn clean install -DskipTests

build:
	@docker build -t project-auth-server .

run:
	@docker-compose up -d

stop:
	@docker stop project-auth-server

sh:
	@docker exec -it project-auth-server /bin/sh

log:
	@docker logs -f project-auth-server
