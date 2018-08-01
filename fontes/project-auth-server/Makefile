build:
	@mvn clean install -DskipTests && sudo docker build -t project-auth-server .

run:
	@sudo docker-compose up -d

stop:
	@sudo docker stop project-auth-server

sh:
	@sudo docker exec -it project-auth-server /bin/sh

log:
	@sudo docker logs -f project-auth-server
