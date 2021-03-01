FROM openjdk:8
MAINTAINER admin
COPY target/SparkAws-1.0-SNAPSHOT-jar-with-dependencies.jar /home/
RUN ls -l /home
CMD ["java","-jar","/home/SparkAws-1.0-SNAPSHOT-jar-with-dependencies.jar"]