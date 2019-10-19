FROM alpine:3.10.1

RUN    apk --no-cache add openjdk11 \
    && rm -rf /var/cache/apk/* \
    && /usr/lib/jvm/java-11-openjdk/bin/jlink \
       --module-path /usr/lib/jvm/java-11-openjdk/jmods \
       --compress=2 \
      --add-modules java.base,java.logging,java.xml \
      --no-header-files \
      --no-man-pages \
      --output /opt/openjdk-11-minimal

FROM alpine:3.10.1
MAINTAINER tamada

COPY --from=0 /opt/openjdk-11-minimal /opt/openjdk-11-minimal

RUN apk --no-cache add curl unzip \
    && curl -s -L -O https://github.com/tamada/9rules/releases/download/v1.0.0/9rules-1.0.0-bin.zip \
    && unzip -q 9rules-1.0.0-bin.zip        \ 
    && mv 9rules-1.0.0 /opt                 \
    && ln -s /opt/9rules-1.0.0 /opt/9rules  \
    && rm 9rules-1.0.0-bin.zip              \
    && sed 's/CELLAR=./CELLAR=\/opt\/9rules/g' /opt/9rules/bin/9rules.sh > /usr/bin/9rules.sh \
    && chmod 755 /usr/bin/9rules.sh

ENV JAVA_HOME=/opt/openjdk-11-minimal 
ENV PATH=$PATH:$JAVA_HOME/bin

ENTRYPOINT [ "9rules.sh" ]
