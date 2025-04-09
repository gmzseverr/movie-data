# Build aşaması
FROM maven:3.8.5-openjdk-17 AS build

# Projeyi konteynıra kopyalıyoruz
COPY . /usr/src/app

# Çalışma dizinini değiştiriyoruz
WORKDIR /usr/src/app

# Maven ile projeyi paketliyoruz
RUN mvn clean package -DskipTests

# Çalıştırma aşaması
FROM openjdk:17.0.1-jdk-slim

# JAR dosyasını build aşamasından kopyalıyoruz
COPY --from=build /usr/src/app/target/i-movie-spring-0.0.1-SNAPSHOT.jar demo.jar

# Uygulama 8080 portunda çalışacak
EXPOSE 8080

# Uygulama çalıştırılacak
ENTRYPOINT ["java", "-jar", "demo.jar"]
