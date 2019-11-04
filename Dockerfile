
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD build/libs/actionservice*.jar actionservice.jar
EXPOSE 8080
CMD ["java", "-jar", "actionservice.jar"]
