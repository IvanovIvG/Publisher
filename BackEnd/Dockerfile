FROM openjdk:23-jdk AS builder
COPY ../BackEnd/.mvn .mvn
COPY ../BackEnd/mvnw ../BackEnd/pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./BackEnd/src ./src
RUN ./mvnw clean install -DskipTests

FROM openjdk:23-jdk
COPY --from=builder target/*.jar *.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "*.jar"]