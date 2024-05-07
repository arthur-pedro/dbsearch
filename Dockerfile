FROM ubuntu:latest
LABEL authors="arthurpedro"

ARG DBSEARCH_VERSION=0.0.1
ARG GIT_URL="https://github.com/arthur-pedro/dbsearch.git"

RUN mkdir -p /app
WORKDIR /app

# Update and upgrade
RUN apt-get update && apt-get upgrade -y
# Java 17
RUN apt-get install -y openjdk-17-jdk
# Maven
RUN apt-get install -y maven
# Git and clone
RUN apt-get install git -y && git clone ${GIT_URL} .
# Build
RUN mvn clean install
# Copy tenat files
COPY api/tenants /app/api/tenants

# Expose port
EXPOSE 8080

CMD ["java", "-jar", "api/target/api-${DBSEARCH_VERSION}-SNAPSHOT.jar"]

CMD ["tail", "-f", "/dev/null"]