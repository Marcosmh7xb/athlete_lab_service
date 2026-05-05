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
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    data_criacao DATE,
    tipo_usuario VARCHAR(15),
    ativo BOOLEAN DEFAULT TRUE
    );
