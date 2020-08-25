build-test-db:
	docker build -t test-db --file Dockerfile-test-db .

test-db: build-test-db
	docker run --rm -d -p 3306:3306 test-db	

tests: test-db
	./mvnw clean test

artifact:
	./mvnw clean package -DskipTests

build-api: artifact
	docker build -t pessoas-api .

run:
	docker-compose up

setup:
	chmod +x mvnw
