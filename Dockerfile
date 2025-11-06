# =======================================================
# ステージ 1: ビルド (build)
# MavenとJDK 17を含むイメージを使用し、JARファイルを生成
# =======================================================
FROM maven:3-eclipse-temurin-17 AS build

# アプリケーションのコードをコンテナにコピー
COPY . /app
WORKDIR /app

# Mavenを使ってアプリケーションをビルド
# -DskipTests: テストをスキップ（デプロイ時間を短縮）
RUN mvn clean package -DskipTests

# =======================================================
# ステージ 2: 実行 (runtime)
# JRE 17のみを含む軽量なイメージを使用
# =======================================================
FROM eclipse-temurin:17-jre-alpine

# Renderのポート自動割り当てに対応するため、8080を設定
# Spring Boot側で server.port=${PORT:8080} のようにしておくと確実です。
ENV PORT 8080

# ビルドステージから生成されたJARファイルをコピー
# ここをプロジェクトに合わせて変更してください: your-app-name.jar
# 例: spring-boot-demo-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/Odekake-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

# JVMのメモリ設定
# Freeプラン(512MB)で安定させるために設定を推奨
ENV JAVA_OPTS="-Xmx400m -Xms200m"

# コンテナ起動時にSpring Bootアプリケーションを実行
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "/usr/local/lib/app.jar"]