PWD := ${CURDIR}
DOCKER_IMAGE_NAME := "www9rules"
CONTAINER_REMOVE_FLAG := "--rm"
BASE_URL := "https://tamada.github.io/9rules"

start:
	docker run ${CONTAINER_REMOVE_FLAG} -d --name ${DOCKER_IMAGE_NAME} -p 1313:1313 -v "${PWD}":/src -v "$(PWD)"/public:/output -e HUGO_THEME=cayman-hugo-theme -e HUGO_WATCH="true" -e HUGO_BASEURL=${BASE_URL} jojomi/hugo:latest

stop:
	docker stop ${DOCKER_IMAGE_NAME}

build:
	docker run ${CONTAINER_REMOVE_FLAG} -d --name ${DOCKER_IMAGE_NAME} -p 1313:1313 -v "${PWD}":/src -v ${PWD}/public:/output -e HUGO_THEME=cayman-hugo-theme -e HUGO_BASEURL=${BASE_URL} jojomi/hugo:latest
