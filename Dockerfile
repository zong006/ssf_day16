# ---------------------------- STAGE 1 ----------------------------
FROM maven:3.9.9-eclipse-temurin-23 AS compiler

LABEL MAINTAINER="example name"
LABEL DESCRIPTION="an example description"
LABEL version="0.0.0"
LABEL name="example app name"

ARG COMPIILE_DIR=/code_folder

WORKDIR ${COMPIILE_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn 

RUN mvn package -Dmaven.test.skip=true

# ---------------------------- STAGE 1 ----------------------------

# ---------------------------- STAGE 2 ----------------------------

FROM maven:3.9.9-eclipse-temurin-23

ARG DEPLOY_DIR=/app

# directory where either source code resides or i copy my project to 
WORKDIR ${DEPLOY_DIR}
COPY --from=compiler /code_folder/target/ssf_day16-0.0.1-SNAPSHOT.jar ssf_day16.jar


ENV SERVER_PORT=3000
EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar target/ssf_day16.jar

# ---------------------------- STAGE 2 ----------------------------