FROM openjdk:18
ARG JAR_FILE=target/My*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 3030
ENTRYPOINT ["java","-jar","/app.jar"]
