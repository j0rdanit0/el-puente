FROM public.ecr.aws/docker/library/eclipse-temurin:21-jdk-alpine AS jre-build

WORKDIR /el-puente
COPY target/public-website.jar ./app.jar

RUN apk update  \
    && apk upgrade \
    && apk add binutils \
    && apk cache clean \
    && jar xf app.jar \
    && jdeps  \
        --ignore-missing-deps -q  \
        --recursive  \
        --multi-release 21  \
        --print-module-deps  \
        --class-path 'BOOT-INF/lib/*'  \
        app.jar > deps.info \
    && jlink  \
        --module-path /usr/lib/jvm/default-jvm/jmods  \
        --add-modules $(cat deps.info),jdk.crypto.ec  \
        --strip-debug  \
        --compress zip-6  \
        --no-header-files  \
        --no-man-pages  \
        --output /minimal-jre

FROM public.ecr.aws/docker/library/alpine:3.18.4 AS final-build

WORKDIR /el-puente
COPY --from=jre-build /el-puente/app.jar ./app.jar

# Copy over minimal JRE
ENV JAVA_HOME="/opt/jre"
COPY --from=jre-build /minimal-jre $JAVA_HOME

# Put the java command on the path
ENV PATH="$JAVA_HOME/bin:$PATH"

EXPOSE 8091

RUN apk update  \
    && apk upgrade  \
    && apk add curl  \
    && apk cache clean

ENTRYPOINT ["java", "-jar", "app.jar"]