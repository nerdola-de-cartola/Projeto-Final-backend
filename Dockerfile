FROM maven:3.9.8-amazoncorretto-21-al2023

WORKDIR .
COPY .mvn/ ./.mvn
COPY src ./src
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
RUN mvn clean install
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "target/api-0.0.1-SNAPSHOT.jar" ]