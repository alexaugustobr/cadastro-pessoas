FROM openjdk:14.0.2-jdk-oracle

WORKDIR /app

COPY target/cadastro-pessoas-0.0.1-SNAPSHOT.jar /app/pessoas.jar

EXPOSE 8080

CMD ["java", "-jar", "pessoas.jar"]