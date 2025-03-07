FROM openjdk:23-jdk AS builder
COPY ../.mvn .mvn
COPY ../mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM openjdk:23-jdk
COPY --from=builder target/*.jar *.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "*.jar"]