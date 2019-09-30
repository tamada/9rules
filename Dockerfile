FROM amazoncorretto:11
MAINTAINER Haruaki Tamada

ENV JAVA_HOME=/etc/alternatives/java_sdk

RUN yum install -y unzip && \
    curl -L -O https://github.com/tamada/9rules/releases/download/v1.0.0/9rules-1.0.0-bin.zip && \
    unzip 9rules-1.0.0-bin.zip           && \
    mv 9rules-1.0.0 /opt                 && \
    ln -s /opt/9rules-1.0.0 /opt/9rules  && \
    rm 9rules-1.0.0-bin.zip              && \
    sed 's/CELLAR=./CELLAR=\/opt\/9rules/g' /opt/9rules/bin/9rules.sh > /usr/bin/9rules.sh && \
    chmod 755 /usr/bin/9rules.sh

ENTRYPOINT [ "9rules.sh" ]
