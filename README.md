# Introduction
This project provides non-business BFF services for Rasa.

# Spring Boot、Gradle、Docker
Spring Boot、Gradle、Docker、Detekt、Jacoco(plugin)

# Build with Docker
1. build:
    ```shell script
    gradle build
    ```
1. build Docker image:
    ```shell script
    docker build -t aj/georgia .
    ```       
1. run Docker image:
    ```shell script
    docker run -p 8089:8080 -t aj/georgia
    ```
