FROM openjdk:23-jdk
ARG JAR_FILE=target/*.jar
COPY database_properties.env database_properties.env
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

