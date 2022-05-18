FROM openjdk:11
ADD target/manytomany.jar manytomany.jar
ENTRYPOINT ["java", "-jar","manytomany.jar"]
EXPOSE 8080