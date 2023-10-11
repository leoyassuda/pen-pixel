include ./infra/.env
include ./infra/.env.pgdb

build-app-image:
	docker build --no-cache -f ./infra/Dockerfile --tag=$(DOCKER_IMAGE_NAME):latest .

get-network-db:
	docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(DOCKER_DB_CONTAINER_NAME)

get-network-redis:
	docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(DOCKER_REDIS_CONTAINER_NAME)

install:
	./mvnw clean install

build-db:
	docker build --no-cache -f ./infra/Dockerfile.postgres -t $(DOCKER_DB_CONTAINER_NAME):latest ./infra

start-infra:
	docker compose -f ./infra/docker-compose.yaml up postgres redis pg_admin -d

start-app:
	docker compose -f ./infra/docker-compose.yaml up app -d

stop-app:
	docker compose -f ./infra/docker-compose.yaml down app

start:
	docker compose -f ./infra/docker-compose.yaml up -d

stop:
	docker compose -f ./infra/docker-compose.yaml stop

rm-app:
	docker stop $(DOCKER_CONTAINER_NAME)
	docker rm $(DOCKER_CONTAINER_NAME)

set-variables:
	export DB_CONNECTION_IP=$(DB_CONNECTION_IP)
	export POSTGRES_DB=$(POSTGRES_DB)
	export POSTGRES_PASSWORD=$(POSTGRES_PASSWORD)
	export POSTGRES_USER=$(POSTGRES_USER)

unset-variables:
	unset DB_CONNECTION_IP
	unset POSTGRES_DB
	unset POSTGRES_PASSWORD
	unset POSTGRES_USER

restart: stop start
