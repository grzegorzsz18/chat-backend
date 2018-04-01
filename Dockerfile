FROM java:8

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD target/chat-0.0.1-SNAPSHOT.jar app.jar 
RUN mkdir /profiles
COPY ./profile /profile

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/app.jar"]
