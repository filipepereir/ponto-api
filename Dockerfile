FROM openjdk:11 AS builder
MAINTAINER Filipe pereira
WORKDIR /ponto

COPY . .

RUN ./mvnw package

FROM openjdk:11 AS prod
MAINTAINER Filipe pereira
WORKDIR /ponto

COPY --from=builder /ponto/target/ponto-api-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "ponto-api-api-0.0.1-SNAPSHOT.jar"]
