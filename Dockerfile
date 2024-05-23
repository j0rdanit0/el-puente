FROM public.ecr.aws/docker/library/eclipse-temurin:21-jdk-alpine AS jre-build

WORKDIR /el-puente
COPY target/public-website.jar ./public-website.jar

RUN apk update  \
    && apk upgrade \
    && apk add binutils \
    && apk cache clean \
    && jar xf public-website.jar \
    && jdeps  \
        --ignore-missing-deps -q  \
        --recursive  \
        --multi-release 21  \
        --print-module-deps  \
        --class-path 'BOOT-INF/lib/*'  \
        public-website.jar > deps.info \
    && jlink  \
        --module-path /usr/lib/jvm/default-jvm/jmods  \
        --add-modules $(cat deps.info),jdk.crypto.ec  \
        --strip-debug  \
        --compress zip-6  \
        --no-header-files  \
        --no-man-pages  \
        --output /minimal-jre

FROM public.ecr.aws/docker/library/alpine:3.18.4 AS external-dependencies

WORKDIR /el-puente
COPY /images/ ./images/
COPY /downloads/ ./downloads/
COPY /.well-known/ ./.well-known/

RUN apk update  \
    && apk upgrade  \
    && apk add curl  \
    && apk cache clean

EXPOSE 8091

FROM external-dependencies AS final-build

WORKDIR /el-puente
COPY --from=jre-build /minimal-jre /opt/jre/
COPY --from=jre-build /el-puente/public-website.jar ./public-website.jar

ENTRYPOINT ["/opt/jre/bin/java","-jar","public-website.jar"]