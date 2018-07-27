build:
	@mvn clean install -DskipTests && docker build -t project-gateway-server .

run:
	@docker-compose up -d

stop:
	@docker stop project-gateway-server

sh:
	@docker exec -it project-gateway-server /bin/sh

log:
	@docker logs -f project-gateway-server
