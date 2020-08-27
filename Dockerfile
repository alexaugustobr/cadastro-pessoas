FROM openjdk:14.0.2-jdk-oracle

WORKDIR /app

COPY target/cadastro-pessoas-0.0.1-SNAPSHOT.jar /app/pessoas.jar

EXPOSE 8080

RUN curl -L https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz > dockerize.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize.tar.gz \
    && rm dockerize.tar.gz

CMD dockerize -wait tcp://mysql:3306 -timeout 10m java -jar pessoas.jar