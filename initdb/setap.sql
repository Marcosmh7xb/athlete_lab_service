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
    id_perfil_treinador INT AUTO_INCREMENT PRIMARY KEY,
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
    id_usuario INT,
    nome VARCHAR(150) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'ATIVO',
    data_criacao DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
    ) ENGINE=InnoDB;

CREATE TABLE admin_treino (
                              id_treino SERIAL PRIMARY KEY,
                              nome VARCHAR(120) NOT NULL,
                              categoria VARCHAR(80),
                              status VARCHAR(20),
                              data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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


CREATE TABLE IF NOT EXISTS perfil_atleta (
    id_perfil_atleta INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT UNIQUE,
    modalidade VARCHAR(100),
    nivel_experiencia VARCHAR(100),
    objetivo TEXT,
    altura FLOAT,
    peso FLOAT,
    dias_semana VARCHAR(100),
    ambiente VARCHAR(100),
    sexo VARCHAR(50),
    restricao_fisica TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
  );

CREATE TABLE  IF NOT EXISTS treino_atribuido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_treino INT NOT NULL,
    id_usuario INT NOT NULL,   -- atleta
    id_treinador INT NOT NULL,
    data_atribuicao DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (id_treino) REFERENCES treino(id_treino) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_treinador) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE  IF NOT EXISTS equipe (
    id_equipe INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    esporte VARCHAR(50),
    id_treinador INT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_treinador) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);


CREATE TABLE  IF NOT EXISTS equipe_atleta (
    id_equipe INT,
    id_atleta INT,
    data_ingresso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_equipe, id_atleta),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe) ON DELETE CASCADE,
    FOREIGN KEY (id_atleta) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

INSERT INTO usuario
(nome, email, telefone, cidade_uf, senha, data_nascimento, tipo_usuario, ativo)
VALUES

    ('Administrador','admin@gmail.com','(77) 99999-9999','Guanambi - BA','admin123','2000-01-01','ADMIN',true);


