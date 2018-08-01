build:
	@mvn clean install -DskipTests && sudo docker build -t project-gateway-server .

run:
	@sudo docker-compose up -d

stop:
	@sudo docker stop project-gateway-server

sh:
	@sudo docker exec -it project-gateway-server /bin/sh

log:
	@sudo docker logs -f project-gateway-server
