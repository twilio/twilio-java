# In this case the Makefile is fairly simple. Still providing it to provide
# consistency with other helper libraries
.PHONY: test install clean

install:
	mvn install

test:
	mvn test

clean:
	rm -rf target
