FROM amazoncorretto:17 AS build

WORKDIR /home/app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew build -x test --stacktrace --info

FROM amazoncorretto:17-alpine

WORKDIR /app
COPY --from=build /home/app/build/libs/Odekake-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-render-deploy.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","/usr/local/lib/spring-render-deploy.jar"]