FROM openjdk:23-jdk AS builder
COPY ../SecurityServer/.mvn .mvn
COPY ../SecurityServer/mvnw ../SecurityServer/pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ../SecurityServer/src ./src
RUN ./mvnw clean install -DskipTests

FROM openjdk:23-jdk
COPY --from=builder target/*.jar *.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "*.jar"]