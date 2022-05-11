FROM openjdk:8
WORKDIR /app
ADD build/libs/MHBA.jar .
ADD build/libs/log4j.properties .
EXPOSE 8081
ENTRYPOINT ["java","-jar","MHBA.jar"]
