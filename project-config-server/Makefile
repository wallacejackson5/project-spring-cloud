buildProject:
	@mvn clean install -DskipTests

build:
	@sudo docker build -t project-config-server .

run:
	@sudo docker-compose up -d

stop:
	@sudo docker stop project-config-server

sh:
	@sudo docker exec -it project-config-server /bin/sh

log:
	@sudo docker logs -f project-config-server
