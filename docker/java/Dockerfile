## stage build
FROM gradle:5.2.1-jdk8-alpine AS build

COPY --chown=gradle:gradle build.gradle settings.gradle gradlew /home/gradle/src/

WORKDIR /home/gradle/src

COPY gradle ./gradle

COPY . .

USER root

RUN ./gradlew build   --stacktrace || return 0

## final image
FROM openjdk:8-jre-slim

ENV USER=dojo-kleer

RUN addgroup --gid 1001 --system $USER && adduser -u 1001 --system $USER --gid 1001

COPY --from=build /home/gradle/src/build/libs/dojo-0.0.1-SNAPSHOT.jar /app/dojo-0.0.1-SNAPSHOT.jar

RUN chown $USER:$USER /app -R

USER $USER

EXPOSE 8080

CMD java -jar /app/dojo-0.0.1-SNAPSHOT.jar

