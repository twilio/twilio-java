.PHONY: install analysis test docs

install:
	@java -version || (echo "Java is not installed, please install Java >= 7"; exit 1);
	mvn clean install

analysis:
	mvn checkstyle:check

test:
	mvn test

docs:
	mvn javadoc:javadoc

API_DEFINITIONS_SHA=$(shell git log --oneline | grep Regenerated | head -n1 | cut -d ' ' -f 5)
docker-build:
	docker build -t twilio/twilio-java .
	docker tag twilio/twilio-java twilio/twilio-java:${TRAVIS_TAG}
	docker tag twilio/twilio-java twilio/twilio-java:apidefs-${API_DEFINITIONS_SHA}
	docker tag twilio/twilio-java twilio/twilio-java:latest

docker-push:
	echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
	docker push twilio/twilio-java:${TRAVIS_TAG}
	docker push twilio/twilio-java:apidefs-${API_DEFINITIONS_SHA}
	docker push twilio/twilio-java:latest
