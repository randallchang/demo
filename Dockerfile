# Application Build

ARG BASE_IMAGE=user/java:base-api
ARG BASE_IMAGE_APP=openjdk:20-ea-17-jdk

FROM --platform=$BUILDPLATFORM ${BASE_IMAGE} AS build

WORKDIR /app

COPY ./ ./

RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B -q && \
    mvn clean package -q -DskipTests=true

# Application Package

FROM --platform=$BUILDPLATFORM ${BASE_IMAGE_APP} AS base

ARG ENVIRONMENT=sit
ARG PROFILE=sit
ARG APP_NAME=api
ARG DEVELOPER=local
ARG PROJECT_NAME=jkos-app-svc
# ARG GIT_COMMIT

#skywalking
ARG SW_AGENT_COLLECTOR_BACKEND_SERVICES=172.16.12.129:11800
ENV SW_AGENT_NAME=${PROJECT_NAME}-${APP_NAME}
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES=${SW_AGENT_COLLECTOR_BACKEND_SERVICES}
ENV SW_JDBC_TRACE_SQL_PARAMETERS=true
ENV SW_AGENT_SAMPLE=-1

LABEL service=${PROJECT_NAME}-${APP_NAME}
LABEL environment=${ENVIRONMENT}
LABEL developers=${DEVELOPER}

ENV PROFILE ${PROFILE}
# ENV GIT_COMMIT ${GIT_COMMIT}

# Install Application

WORKDIR /app

COPY --from=build /app/${APP_NAME}/target/${APP_NAME}.jar app.jar
COPY --from=build /app/skywalking-agent skywalking-agent

ENTRYPOINT ["java", \
    "-jar", "-javaagent:/app/skywalking-agent/skywalking-agent.jar", \
    "-Xmx512m", "-Xms512m", \
    "-jar", "/app/app.jar", \
    "--spring.profiles.active=${PROFILE}"]
CMD ["--server.address=0.0.0.0"]

EXPOSE 8080
