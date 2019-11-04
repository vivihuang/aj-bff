
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD build/libs/georgia*.jar georgia.jar
EXPOSE 8089
CMD ["java", "-jar", "georgia.jar"]
