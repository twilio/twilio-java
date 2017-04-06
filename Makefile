.PHONY: clean install analysis test docs

install:
	mvn clean install

analysis:
	mvn checkstyle:check

test:
	mvn test

docs:
	mvn javadoc:javadoc
