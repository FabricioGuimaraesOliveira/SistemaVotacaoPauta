FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/votacao-0.0.1-SNAPSHOT.jar sistemavotacao.jar

ENTRYPOINT ["java","-jar","/sistemavotacao.jar"]