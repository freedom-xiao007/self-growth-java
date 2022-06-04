FROM openjdk:17-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories && apk update && apk add busybox-extras
ADD ./target/record-0.0.1-SNAPSHOT.jar /app/record-0.0.1-SNAPSHOT.jar
CMD java -jar /app/record-0.0.1-SNAPSHOT.jar