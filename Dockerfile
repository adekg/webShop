FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar webshop-boot-docker.jar
ENTRYPOINT ["java","-jar","/webshop-boot-docker.jar"]
