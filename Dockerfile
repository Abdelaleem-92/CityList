FROM openjdk:8
EXPOSE 8080
ADD target/cityList-0.0.1.jar cityList.jar
ENTRYPOINT ["java", "-jar", "/cityList.jar"]