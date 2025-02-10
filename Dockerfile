FROM openjdk:23-jdk as builder
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM openjdk:23-jdk
COPY --from=builder target/*.jar *.jar
COPY database_properties.env database_properties.env
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "*.jar"]