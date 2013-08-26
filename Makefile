# In this case the Makefile is fairly simple. Still providing it to provide
# consistency with other helper libraries
.PHONY: test install clean authors

install:
	mvn install

test:
	mvn test

clean:
	rm -rf target

authors:
	echo "Authors\n=======\n\nA huge thanks to all of our contributors:\n\n" > AUTHORS.md
	git log --raw | grep "^Author: " | cut -d ' ' -f2- | cut -d '<' -f1 | sed 's/^/- /' | sort | uniq >> AUTHORS.md
