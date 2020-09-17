PWD := ${CURDIR}
DOCKER_IMAGE_NAME := www9rules
JOJOMI_VERSION := 0.69.0
DOCKER_IMAGE := jojomi/hugo:${JOJOMI_VERSION}

define __docker_run
	docker run --rm --name $1 $2 --interactive --volume ${PWD}:/src --volume ${PWD}/public:/output --workdir /src --entrypoint hugo $(DOCKER_IMAGE) $3
endef

define __commit_and_push
	git -C $1 commit -a -m $2
	git -C $1 push origin gh-pages
endef

build:
	$(call __docker_run,${DOCKER_IMAGE_NAME}_build,--tty,--buildFuture)

start:
	$(call __docker_run,${DOCKER_IMAGE_NAME},-d --publish 1313:1313,server --bind="0.0.0.0" --buildFuture)

stop:
	docker stop ${DOCKER_IMAGE_NAME}

pull:
	git -C public pull origin gh-pages

deploy: pull build
	$(call __commit_and_push,public,"automatically update for publish the web pages")
