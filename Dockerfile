FROM amazoncorretto:17 AS build

COPY wallet /app/wallet
WORKDIR /home/app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew build -x test --stacktrace --info

FROM amazoncorretto:17-alpine

WORKDIR /app

# Wallet をコピー
COPY wallet /app/wallet

# Java truststore に Wallet の証明書を登録
RUN keytool -importcert -alias oracle-root \
    -file /app/wallet/root.pem \
    -keystore /usr/lib/jvm/default-jvm/lib/security/cacerts \
    -storepass changeit -noprompt

RUN keytool -importcert -alias oracle-intermediate \
    -file /app/wallet/intermediate.pem \
    -keystore /usr/lib/jvm/default-jvm/lib/security/cacerts \
    -storepass changeit -noprompt

COPY --from=build /home/app/build/libs/Odekake-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-render-deploy.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","-DTNS_ADMIN=/app/wallet","/usr/local/lib/spring-render-deploy.jar"]
