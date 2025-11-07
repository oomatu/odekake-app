FROM eclipse-temurin:17-jdk

ENV DB_URL=jdbc:oracle:thin:@odekakedb_medium?TNS_ADMIN=/app/wallet
ENV DB_USER=ADMIN
ENV DB_PASSWORD=koreGApassw0rd

WORKDIR /app

COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

RUN chmod +x gradlew

COPY . /app

RUN ./gradlew build --stacktrace --info

CMD ["java", "-jar", "build/libs/Odekake-0.0.1-SNAPSHOT.jar"]