FROM openjdk:11-jdk
COPY target/cms.jar app.jar
COPY wait-for-it.sh .
RUN ["chmod", "+x", "./wait-for-it.sh"]
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "db:3307", "--", "java", "-jar", "/app.jar"]