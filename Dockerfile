FROM alpine:3.10.1 AS base

RUN    apk --no-cache add openjdk11=11.0.4_p4-r1 \
    && rm -rf /var/cache/apk/* \
    && /usr/lib/jvm/java-11-openjdk/bin/jlink \
       --module-path /usr/lib/jvm/java-11-openjdk/jmods \
       --compress=2 \
      --add-modules java.base,java.logging,java.xml \
      --no-header-files \
      --no-man-pages \
      --output /opt/openjdk-11-minimal

FROM alpine:3.10.1

ARG ninerules_version="1.1.1"

LABEL maintainer="Haruaki Tamada" \
      version="${ninerules_version}" \
      description="Checking tool for object oriented exercises by nine rules. "

COPY --from=base /opt/openjdk-11-minimal /opt/openjdk-11-minimal

RUN    adduser -D ninerules \
    && apk --no-cache add --virtual .builddeps curl unzip \
    && curl -s -L -O "https://github.com/tamada/9rules/releases/download/v${ninerules_version}/9rules-${ninerules_version}-bin.zip" \
    && unzip -q "9rules-${ninerules_version}-bin.zip"        \
    && mv "9rules-${ninerules_version}" /opt                 \
    && ln -s "/opt/9rules-${ninerules_version}" /opt/9rules  \
    && rm "9rules-${ninerules_version}-bin.zip"              \
    && sed 's/CELLAR=./CELLAR=\/opt\/9rules/g' /opt/9rules/bin/9rules.sh > /usr/bin/9rules.sh \
    && chmod 755 /usr/bin/9rules.sh \
    && apk del --purge .builddeps

ENV JAVA_HOME="/opt/openjdk-11-minimal"
ENV PATH="$PATH:$JAVA_HOME/bin"
ENV HOME="/home/ninerules"

WORKDIR /home/ninerules
USER    ninerules

ENTRYPOINT [ "9rules.sh" ]
