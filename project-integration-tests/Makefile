#!make

start:
	@mvn test -Dcucumber.filter.tags="@test"; \
	docker-compose -f docker-compose.yml down; \
	(sleep 10 && python3 ./scripts/wiremock-init-data.py)& \
	docker-compose -f docker-compose.yml up --force-recreate --build --abort-on-container-exit; \
	docker rm -f project-integration-tests

clean:
	@docker rm project-integration-tests -f; \
	docker rmi project-integration-tests/wiremock:latest; \
	mvn clean package -DskipTests; \
	mvn clean package -DskipTests

dockerClean:
	@docker ps -a -q | xargs docker stop && docker ps -a -q | xargs docker rm; \
	docker network prune -f; \
	docker images -a --format "{{.ID}}" | xargs docker rmi -f; \
	docker volume prune -f
