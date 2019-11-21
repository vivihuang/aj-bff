FROM openjdk:11-jdk-slim
VOLUME /tmp

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ARG IMAGE_VERSION
ENV IMAGE_VERSION=$IMAGE_VERSION

ADD build/libs/georgia*.jar georgia.jar
EXPOSE 8080
CMD ["java","-Dimage.version=${IMAGE_VERSION}", "-jar", "georgia.jar"]
