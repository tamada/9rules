PWD := ${CURDIR}
DOCKER_IMAGE_NAME := www9rules
DOCKER_IMAGE := jojomi/hugo:latest

define _docker_run
	docker run --rm --name $1 $2 --interactive --volume ${PWD}:/src --volume ${PWD}/public:/output --workdir /src --entrypoint hugo $(DOCKER_IMAGE) $3
endef

build:
	$(call _docker_run,${DOCKER_IMAGE_NAME}_build,--tty,--buildFuture)

start:
	$(call _docker_run,${DOCKER_IMAGE_NAME},-d --publish 1313:1313,server --bind="0.0.0.0" --buildFuture)

stop:
	docker stop ${DOCKER_IMAGE_NAME}

