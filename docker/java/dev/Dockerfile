## stage build
FROM gradle:5.2.1-jdk8-alpine

USER root

COPY --chown=gradle:gradle build.gradle settings.gradle gradlew /home/gradle/src/

WORKDIR /home/gradle/src

COPY gradle ./gradle