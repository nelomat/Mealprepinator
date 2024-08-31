# Verwende das offizielle OpenJDK-Image als Basis
FROM openjdk:22

# Setze das Arbeitsverzeichnis
WORKDIR /backend

# Kopiere die JAR-Datei in das Arbeitsverzeichnis
COPY build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
