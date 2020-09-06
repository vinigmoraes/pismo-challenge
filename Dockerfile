FROM openjdk:11-jdk

ENV JAR_NAME pismo-challenge-all.jar

WORKDIR /pismo-challenge

COPY build/libs/$JAR_NAME $JAR_NAME

EXPOSE 8080

CMD java -jar $JAR_NAME
