FROM openjdk:8-jre-alpine

# grab wiremock standalone jar
COPY ./lib/wiremock-jre8-standalone-2.25.1.jar /home/wiremock/lib/wiremock-jre8-standalone-2.25.1.jar

WORKDIR /home/wiremock

VOLUME /home/wiremock/mappings
EXPOSE 9090

CMD java -jar ./lib/wiremock-jre8-standalone-2.25.1.jar --port 9090 --verbose --local-response-templating
