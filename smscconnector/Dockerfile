FROM adoptopenjdk/openjdk11:ubi
ENV TZ="Asia/Kolkata"
COPY ./target/smscconnector-1.0.jar /smscconnector.jar
ENTRYPOINT ["java","-jar","/smscconnector.jar"]
