FROM ubuntu-jdk

MAINTAINER PP "blueplanet19x@gmail.com"

ENV version=aws-db-usage
ENV dbuser=postgres
ENV dbpass=password123
#ENV jdbcurl=jdbc:driver://hostname:port/dbName
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.c3vy1jfbuzrx.eu-west-1.rds.amazonaws.com:5432/postgres


WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]