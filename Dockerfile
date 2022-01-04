FROM openjdk
COPY /target/ponto-api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar" , "ponto-api-0.0.1-SNAPSHOT.jar", "Pprod"]