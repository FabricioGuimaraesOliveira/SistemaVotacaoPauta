FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/votacao-0.0.1-SNAPSHOT.jar votacao.jar

ENTRYPOINT ["java","-jar","/votacao.jar"]