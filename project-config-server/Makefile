build:
	@mvn clean install -DskipTests && docker build -t project-config-server .

run:
	@docker-compose up -d

stop:
	@docker stop project-config-server

sh:
	@docker exec -it project-config-server /bin/sh

log:
	@docker logs -f project-config-server
