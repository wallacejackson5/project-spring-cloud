run:
	@sudo chown root config/filebeat.yml && sudo chmod go-w config/filebeat.yml && sudo docker-compose up -d

stop:
	@sudo docker stop filebeat_elk && sudo docker rm filebeat_elk

sh:
	@sudo docker exec -it filebeat_elk /bin/bash

log:
	@sudo docker logs -f filebeat_elk
