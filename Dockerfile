# Usando uma imagem base Java
FROM openjdk:11-jdk-slim

# Definindo o diretório de trabalho no contêiner
WORKDIR /

# Copiando o arquivo JAR do aplicativo para o contêiner
COPY src/main/java/App.jar App.jar

# Expondo a porta 8080 para acessar o aplicativo
EXPOSE 8080

# Definindo as variáveis de ambiente para o MongoDB Compass
ENV MONGODB_URI mongodb+srv://miguel:456456hghg@cluster0.0aadjeb.mongodb.net/

# Instalando o MongoDB Compass
RUN apt-get update && \
    apt-get install -y wget gnupg && \
    wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add - && \
    echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/debian bullseye/mongodb-org/4.4 main" | tee /etc/apt/sources.list.d/mongodb-org-4.4.list && \
    apt-get update && \
    apt-get install -y mongodb-database-tools && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Definindo o comando de inicialização do aplicativo
CMD ["java", "-jar", "App.jar"]
