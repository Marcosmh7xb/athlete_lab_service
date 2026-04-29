-- BANCO DE DADOS: ATHLETE LAB
-- Gerado a partir do diagrama UML enviado

CREATE DATABASE IF NOT EXISTS athlete_lab;
USE athlete_lab;


-- TABELA: Usuario (classe base)
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    cidade_uf VARCHAR(100),
    senha VARCHAR(15) NOT NULL,
    data_nascimento DATE,
    data_criacao DATE,
    tipo_usuario VARCHAR(15),
    ativo BOOLEAN DEFAULT TRUE
    );

INSERT INTO usuario (nome,email,telefone,data_nascimento,cidade_uf,data_criacao,ativo,senha, tipo_usuario) VALUES
      ('João Silva','joao@email.com','11999999999','1995-03-10','São Paulo - SP',CURDATE(),TRUE,'123', 'ATLETA');
