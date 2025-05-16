# Build aşaması
FROM maven:3.8.5-openjdk-17 AS build

# Çalışma dizinini ayarla
WORKDIR /usr/src

# Projeyi konteynıra kopyala
COPY . .

# Projeyi build et (testleri atla)
RUN mvn clean package -DskipTests

# Runtime aşaması
FROM openjdk:17.0.1-jdk-slim

# Çalışma dizinini ayarla
WORKDIR /app

# JAR dosyasını build aşamasından kopyala
COPY --from=build /usr/src/target/i-movie-spring-0.0.1-SNAPSHOT.jar app.jar

# Port aç
EXPOSE 8080

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
