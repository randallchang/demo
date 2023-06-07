# Define Required Command
DOCKER_CMD=docker
ANSIBLE_PLAYBOOK_CMD=ansible-playbook
GIT_CMD=git
SED_CMD=sed
KUBECTL_CMD=kubectl

# System Environment
APP_OSTYPE :=
ifeq ($(OS),Windows_NT)
	APP_OSTYPE = Windows
else
	UNAME_S := $(shell uname -s)
	ifeq ($(UNAME_S),Linux)
		APP_OSTYPE = Linux
	endif
	ifeq ($(UNAME_S),Darwin)
		APP_OSTYPE = Darwin
	endif
endif

build-base-image:
	$(DOCKER_CMD) buildx use $(_BUILD_ENV)
	$(DOCKER_CMD) buildx ls
	$(DOCKER_CMD) buildx build \
	-f $(_DOCKERFILE_BASE_IMAGE) \
	--platform linux/arm,linux/arm64,linux/amd64 \
	--build-arg BASE_IMAGE_BUILD_FROM=$(_BASE_IMAGE_BUILD_FROM) \
	-t $(_BASE_IMAGE) . --push

push-image:
	$(DOCKER_CMD) buildx use $(_BUILD_ENV)
	$(DOCKER_CMD) buildx ls
	$(DOCKER_CMD) buildx build \
	-f $(_DOCKERFILE) \
	--platform linux/arm,linux/arm64,linux/amd64 \
	--build-arg BASE_IMAGE=$(_BASE_IMAGE) \
	--build-arg BASE_IMAGE_APP=$(_BASE_IMAGE_APP) \
	--build-arg PROFILE=$(_PROFILE) \
	--build-arg APP_NAME=$(_APP_NAME) \
	--build-arg DEVELOPER=$(_DEVELOPER) \
	--build-arg ENVIRONMENT=$(_ENVIRONMENT) \
	--build-arg GIT_COMMIT=$(_GIT_COMMIT) \
	--build-arg PROJECT_NAME=$(_PROJECT_NAME) \
	--build-arg SW_AGENT_COLLECTOR_BACKEND_SERVICES=$(_SW_AGENT_COLLECTOR_BACKEND_SERVICES) \
	-t $(_APP_IMAGE):$(_GIT_COMMIT) . --push

clean-image:
	$(DOCKER_CMD) buildx prune -a -f

deploy:
	$(ANSIBLE_PLAYBOOK_CMD) -i $(_INVENTORY) -e hn=$(_HN) $(_DEPLOYMENT_PLAYBOOK)

deploy-k8s:
	if [ "$(APP_OSTYPE)" = "Windows" ]; \
	then \
		$(SED_CMD) -i \
		"s|docker-image:sample|$(_DOCKER_IMAGE_TAG_ROOT)/$(_APP_NAME):$(_GIT_COMMIT)|g" \
		$(_DEPLOYMENT_DIRECTORY)/$(_ENVIRONMENT)/$(_APP_NAME)/deployment.yml; \
	elif [ "$(APP_OSTYPE)" = "Darwin" ]; \
	then \
		$(SED_CMD) -i "" \
		"s|docker-image:sample|$(_DOCKER_IMAGE_TAG_ROOT)/$(_APP_NAME):$(_GIT_COMMIT)|g" \
		$(_DEPLOYMENT_DIRECTORY)/$(_ENVIRONMENT)/$(_APP_NAME)/deployment.yml; \
	elif [ "$(APP_OSTYPE)" = "Linux" ]; \
	then \
		$(SED_CMD) -i \
		"s|docker-image:sample|$(_DOCKER_IMAGE_TAG_ROOT)/$(_APP_NAME):$(_GIT_COMMIT)|g" \
		$(_DEPLOYMENT_DIRECTORY)/$(_ENVIRONMENT)/$(_APP_NAME)/deployment.yml; \
	fi

	$(KUBECTL_CMD) -n $(_K8S_NAMESPACE) apply -f $(_DEPLOYMENT_DIRECTORY)/$(_ENVIRONMENT)/$(_APP_NAME)
	$(GIT_CMD) checkout $(_DEPLOYMENT_DIRECTORY)/$(_ENVIRONMENT)/$(_APP_NAME)/deployment.yml

help:
	@echo  ''
	@echo  'Build Base Image:'
	@echo  '  make build-base-image'
	@echo  ''
	@echo  'Push Image:'
	@echo  '  make push-image'
	@echo  ''
	@echo  'Clean Image:'
	@echo  '  make clean-image'
	@echo  ''
	@echo  'Deploy Application:'
	@echo  '  make deploy'
	@echo  ''
	@echo  'Deploy K8S Application:'
	@echo  '  make deploy-k8s'
	@echo  ''
