run:
	@sudo sysctl -w vm.max_map_count=262144 && sudo docker-compose up -d

stop:
	@sudo docker stop elk && sudo docker rm elk
	
sh:
	@sudo docker exec -it elk /bin/bash

log:
	@sudo docker logs -f elk
