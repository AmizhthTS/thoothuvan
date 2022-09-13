FROM adoptopenjdk/openjdk11:ubi
ENV TZ="Asia/Kolkata"
COPY ./target/hapi-1.0.jar /hapi.jar
ENTRYPOINT ["java","-jar","/hapi.jar"]
