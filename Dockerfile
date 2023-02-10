# syntax=docker/dockerfile:1
#Which "official Java image" ?
FROM openjdk:20-ea-17-jdk-oracle
#working directory
WORKDIR /app
#copy from your Host(PC, laptop) to container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#Run this inside the image
#RUN ./mvnw dependency:go-offline
COPY src ./src
#run inside container
CMD ["./mvnw", "spring-boot:run"]