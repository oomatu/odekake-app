FROM eclipse-temurin:17-jdk

WORKDIR /app

# 必要ファイルを先にコピー
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# gradlewに実行権限を付与
RUN chmod +x gradlew

# 残りのソースコードをコピー
COPY . /app

# ビルド
RUN ./gradlew build --stacktrace --info


# 実行
CMD ["java", "-jar", "build/libs/Odekake-0.0.1-SNAPSHOT.jar"]