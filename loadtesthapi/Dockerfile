FROM adoptopenjdk/openjdk11:ubi
ENV TZ="Asia/Kolkata"
COPY ./target/loadtesthapi-1.0.jar /loadtesthapi.jar
ENTRYPOINT ["java","-jar","/loadtesthapi.jar"]
