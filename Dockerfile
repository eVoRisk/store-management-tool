FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

RUN mkdir /app

COPY build/libs/store-management-tool-0.0.1-SNAPSHOT.jar /app/store-management-tool.jar

ENTRYPOINT ["java","-jar","/app/store-management-tool.jar"]
