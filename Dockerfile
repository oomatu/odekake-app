FROM amazoncorretto:17-alpine

WORKDIR /app
COPY --from=build /home/app/build/libs/Odekake-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-render-deploy.jar
COPY wallet /app/wallet

EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","-DTNS_ADMIN=/app/wallet","/usr/local/lib/spring-render-deploy.jar"]