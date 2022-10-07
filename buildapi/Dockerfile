FROM adoptopenjdk/openjdk11:ubi
ENV TZ="Asia/Kolkata"
COPY ./target/buildapi-1.0.jar /buildapi.jar
ENTRYPOINT ["java","-jar","/buildapi.jar"]
