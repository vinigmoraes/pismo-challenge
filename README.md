# Pismo Challenge

For building and running the application you need:

- [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/)

## Executing requests

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5f43de35a956b541b88c)

## Running

```
docker-compose up --build
```

#### Running with docker

```shell
./gradlew build && docker-compose up --build
```

##  Testing

```shell
./gradlew test
```

## Deployment

Application is configured to every push to master execute deploy automatic.

## Built With

- [Kotlin](https://kotlinlang.org/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Ktor](https://ktor.io) - Lightweight Web Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform

