build:
	mvn compile

performance-test:
	mvn gatling:test -P performance-test
	
tests:
	mvn test

# integration-test:
# 	mvn test -P integration-test

# system-test
# 	mvn test -P system-test