# Estágio 1: Build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# Estágio 2: Runtime com Tomcat
FROM tomcat:10.1-jdk17

# Remove apps padrão do Tomcat (opcional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado
COPY --from=build \
/app/target/athletelab.war \
/usr/local/tomcat/webapps/

EXPOSE 8080