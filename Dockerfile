FROM public.ecr.aws/docker/library/eclipse-temurin:21.0.1_12-jdk-alpine AS jre-build

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

FROM public.ecr.aws/docker/library/alpine:3.18.4 AS final-build

WORKDIR /el-puente
COPY --from=jre-build /minimal-jre /opt/jre/
COPY target/public-website.jar ./public-website.jar
COPY /images/* ./images/
COPY /downloads/* ./downloads/
COPY /.well-known/* ./.well-known/

RUN apk update  \
    && apk upgrade  \
    && apk add curl  \
    && apk cache clean

EXPOSE 8080

ENTRYPOINT ["/opt/jre/bin/java","-jar","/el-puente/public-website.jar"]