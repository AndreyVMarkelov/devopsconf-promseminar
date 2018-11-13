FROM picoded/ubuntu-openjdk-8-jdk

RUN apt-get update && apt-get install redis-server -y && apt-get install netcat -y

RUN mkdir -p /usr/local/redis-sidecar
COPY @project.build.finalName@.jar /usr/local/redis-sidecar/

COPY run.sh /run.sh
RUN chmod +x /run.sh

CMD /run.sh
