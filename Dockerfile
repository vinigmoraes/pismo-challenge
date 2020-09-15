FROM openjdk:11-jdk

ENV JAR_NAME pismo-challenge-all.jar

COPY build/libs/$JAR_NAME $JAR_NAME

CMD java -jar $JAR_NAME
