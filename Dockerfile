FROM openjdk:20-jdk-slim
MAINTAINER IlanShochat
VOLUME /tmp
COPY target/rest-io.jar rest-io.jar
ENV ENCRYPTING_PASSWORD=${encrypting_password}
ENTRYPOINT ["java","-jar","/rest-io.jar","$ENCRYPTING_PASSWORD"]
