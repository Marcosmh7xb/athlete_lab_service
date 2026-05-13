CREATE DATABASE IF NOT EXISTS athlete_lab;
USE athlete_lab;

-- USUARIO
CREATE TABLE IF NOT EXISTS usuario (
                                       id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                                       nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    cidade_uf VARCHAR(100),
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    data_criacao DATE DEFAULT (CURRENT_DATE),
    tipo_usuario VARCHAR(15),
    foto VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE
    ) ENGINE=InnoDB;

-- PERFIL TREINADOR
CREATE TABLE IF NOT EXISTS perfil_treinador (
                                                id_perfil INT AUTO_INCREMENT PRIMARY KEY,
                                                id_usuario INT NOT NULL,
                                                modalidade VARCHAR(100) NOT NULL,
    nivel_experiencia VARCHAR(50),
    objetivo VARCHAR(255),
    ambiente VARCHAR(100),
    sexo VARCHAR(20),
    restricao_fisica TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
    ) ENGINE=InnoDB;

-- TREINO
CREATE TABLE IF NOT EXISTS treino (
                                      id_treino INT AUTO_INCREMENT PRIMARY KEY,
                                      id_usuario INT NOT NULL,
                                      nome VARCHAR(150) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'ATIVO',
    data_criacao DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
    ) ENGINE=InnoDB;

-- EXERCICIO (AGORA É O MÓDULO OFICIAL)
CREATE TABLE IF NOT EXISTS exercicio (
                                         id_exercicio INT AUTO_INCREMENT PRIMARY KEY,
                                         id_treino INT NOT NULL,
                                         nome VARCHAR(150) NOT NULL,
    series INT DEFAULT 0,
    repeticoes INT DEFAULT 0,
    tempo_min INT DEFAULT 0,
    observacao TEXT,
    FOREIGN KEY (id_treino) REFERENCES treino(id_treino) ON DELETE CASCADE
    ) ENGINE=InnoDB;