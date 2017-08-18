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
