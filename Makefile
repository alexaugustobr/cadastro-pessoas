build-test-db: artifact
	docker build -t test-db --file Dockerfile-test-db .

test-db: build-test-db
	docker run -d -p 3306:3306 --name test-db test-db	

tests:
	chmod +x mvnw
	./mvnw clean test \
		&& docker rm -f test-db
	
artifact:
	chmod +x mvnw
	./mvnw clean package -DskipTests 
	
build-api: artifact
	docker build -t pessoas-api .

run: artifact
	docker-compose up
