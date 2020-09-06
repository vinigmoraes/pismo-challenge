FROM openjdk:11-jdk

ENV JAR_NAME pismo-challenge-all.jar

WORKDIR /app

COPY ./build/libs/$JAR_NAME /app/$JAR_NAME

EXPOSE 8080

CMD java -jar $JAR_NAME
