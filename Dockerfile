FROM maven:3.6.0-jdk-8
ARG JAR_FILE="cityList-0.0.1.jar"
WORKDIR /app
COPY . /app/
RUN mvn -f /app/pom.xml clean install -DskipTests
WORKDIR /app
RUN cp target/${JAR_FILE} /usr/share/${JAR_FILE}
ENTRYPOINT ["java", "-jar", "/usr/share/cityList-0.0.1.jar"]