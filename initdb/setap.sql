-- BANCO DE DADOS: ATHLETE LAB
-- Gerado a partir do diagrama UML enviado

CREATE DATABASE IF NOT EXISTS athlete_lab;
USE athlete_lab;


-- TABELA: Usuario (classe base)
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    cidade_uf VARCHAR(100),
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    data_criacao DATE,
    tipo_usuario VARCHAR(15),
    foto VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS perfil_treinador (

    id_perfil INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    modalidade VARCHAR(100) NOT NULL,
    nivel_experiencia VARCHAR(50),
    objetivo VARCHAR(255),
    ambiente VARCHAR(100),
    sexo VARCHAR(20),
    restricao_fisica TEXT,

    -- Relacionamento com a tabela de usuários
    CONSTRAINT fk_usuario_treinador FOREIGN KEY (id_usuario)
    REFERENCES usuario(id_usuario)
    ON DELETE CASCADE
    );

INSERT INTO usuario (nome,email,telefone,data_nascimento,cidade_uf,data_criacao,ativo,senha, tipo_usuario) VALUES
      ('João Silva','joao@email.com','11999999999','1995-03-10','São Paulo - SP',CURDATE(),TRUE,'$2a$10$7EqJtq98hPqEX7fNZaFWoOHi6q5M7u6czS4QhIwTZPIWJ0xj5nM2', 'ATLETA');
