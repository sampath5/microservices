FROM openjdk:8-jre-alpine
COPY target/EurekaServer-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app

ENTRYPOINT ["java", "-jar", "EurekaServer-0.0.1-SNAPSHOT.jar"]
