# ベースイメージ（Java 17）
FROM openjdk:17-jdk-slim

# 作業ディレクトリを作成
WORKDIR /app

# Gradleキャッシュを活用するなら build.gradle などを先にコピー
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# 依存関係を先に解決（キャッシュ効率化）
RUN ./gradlew dependencies

# 残りのソースコードをコピー
COPY . /app

# ビルド
RUN ./gradlew build

# JARファイルを実行
CMD ["java", "-jar", "build/libs/Odekake-0.0.1-SNAPSHOT.jar"]