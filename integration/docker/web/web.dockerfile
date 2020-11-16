ARG BASE_IMAGE=example_base:latest
FROM $BASE_IMAGE
ARG BOOT_JAR_NAME=web-v1.0.0.jar
COPY build /opt/integration
COPY web/application.yaml /opt/integration/backend/
ENV BOOT_JAR_PATH=/opt/integration/backend/${BOOT_JAR_NAME}
RUN set -x \
    && ln -s /opt/integration/frontend /opt/integration/backend/statics
WORKDIR /opt/integration/backend
CMD /usr/bin/java -jar $BOOT_JAR_PATH
