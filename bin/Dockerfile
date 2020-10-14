FROM openjdk:11-jdk
COPY target/cms.jar app.jar
COPY wait-for-it.sh .
#RUN ["chmod", "+x", "./wait-for-it.sh"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]