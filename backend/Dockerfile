FROM amazoncorretto:21.0.5.11.1-alpine

WORKDIR /app

COPY target/conversor-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

