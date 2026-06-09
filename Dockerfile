# Stage 1: build Maven
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o pom.xml e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Build do WAR
RUN mvn clean package -DskipTests

# Stage 2: Tomcat
FROM tomcat:10.1.15-jdk17

# Limpa apps padrões do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia WAR do stage de build
COPY --from=build /app/target/athletelab.war /usr/local/tomcat/webapps/athletelab.war

# Cria volume para hot reload de JSP/CSS/JS
VOLUME /usr/local/tomcat/webapps/athletelab_web

# Expõe porta
EXPOSE 8080

CMD ["catalina.sh", "run"]