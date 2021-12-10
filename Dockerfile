FROM openjdk:8
EXPOSE 8080
ADD target/cityList.jar cityList.jar
ENTRYPOINT ["java", "-jar", "/cityList.jar"]