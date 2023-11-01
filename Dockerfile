FROM openjdk:17-jdk

COPY target/demoBank.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demoBank.jar"]

