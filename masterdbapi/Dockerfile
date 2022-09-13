FROM adoptopenjdk/openjdk11:ubi
ENV TZ="Asia/Kolkata"
COPY ./target/masterdbapi-1.0.jar /masterdbapi.jar
ENTRYPOINT ["java","-jar","/masterdbapi.jar"]
