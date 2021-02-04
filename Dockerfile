FROM openjdk:8-alpine

WORKDIR /app
COPY ./target/template_manager-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "template_manager-0.0.1-SNAPSHOT.jar"]
