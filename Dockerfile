FROM adoptopenjdk/openjdk11

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} onegane.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "onegane.jar"]