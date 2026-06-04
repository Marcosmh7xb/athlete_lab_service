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

    ('Administrador','admin@gmail.com','(77) 99999-9999','Guanambi - BA','admin123','2000-01-01','ADMIN',true),

    ('Carlos Silva','carlos@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','TREINADOR',true),

    ('Fernanda Rocha','fernanda@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','TREINADOR',true),

    ('Lucas Almeida','lucas@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Mariana Souza','mariana@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Pedro Henrique','pedro@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Ana Clara','ana@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Gabriel Santos','gabriel@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Juliana Lima','juliana@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Rafael Costa','rafael@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Beatriz Martins','beatriz@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true),

    ('Thiago Oliveira','thiago@gmail.com','(77) 99999-9999','Guanambi - BA','123456','2000-01-01','ATLETA',true);

INSERT INTO perfil_treinador
(id_usuario, modalidade, nivel_experiencia, objetivo, ambiente, sexo, restricao_fisica)
VALUES

    (
        (SELECT id_usuario FROM usuario WHERE email = 'carlos@gmail.com'),
        'Musculação',
        'Avançado',
        'Hipertrofia e performance esportiva',
        'Academia',
        'Masculino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email = 'fernanda@gmail.com'),
        'Funcional',
        'Intermediário',
        'Condicionamento físico e emagrecimento',
        'Academia / Ar livre',
        'Feminino',
        NULL
    );

INSERT INTO perfil_atleta
(id_usuario, modalidade, nivel_experiencia, objetivo, altura, peso, dias_semana, ambiente, sexo, restricao_fisica)
VALUES

    (
        (SELECT id_usuario FROM usuario WHERE email='lucas@gmail.com'),
        'Musculação',
        'Iniciante',
        'Ganhar massa muscular',
        1.78,
        72.5,
        'Segunda, Quarta, Sexta',
        'Academia',
        'Masculino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='mariana@gmail.com'),
        'Corrida',
        'Intermediário',
        'Melhorar resistência',
        1.65,
        58.0,
        'Terça, Quinta, Sábado',
        'Ar livre',
        'Feminino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='pedro@gmail.com'),
        'Cross',
        'Intermediário',
        'Condicionamento físico',
        1.82,
        80.0,
        'Segunda a Sexta',
        'Academia',
        'Masculino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='ana@gmail.com'),
        'Yoga',
        'Iniciante',
        'Flexibilidade e bem-estar',
        1.60,
        54.0,
        'Segunda, Quarta',
        'Casa',
        'Feminino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='gabriel@gmail.com'),
        'Futebol',
        'Avançado',
        'Preparação competitiva',
        1.76,
        70.0,
        'Terça, Quinta, Domingo',
        'Campo',
        'Masculino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='juliana@gmail.com'),
        'Natação',
        'Intermediário',
        'Melhorar técnica',
        1.68,
        60.0,
        'Segunda, Quarta, Sexta',
        'Piscina',
        'Feminino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='rafael@gmail.com'),
        'Ciclismo',
        'Avançado',
        'Resistência cardiovascular',
        1.80,
        74.0,
        'Terça, Quinta, Sábado',
        'Ar livre',
        'Masculino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='beatriz@gmail.com'),
        'Pilates',
        'Iniciante',
        'Postura e mobilidade',
        1.63,
        56.0,
        'Segunda e Quinta',
        'Studio',
        'Feminino',
        NULL
    ),

    (
        (SELECT id_usuario FROM usuario WHERE email='thiago@gmail.com'),
        'Calistenia',
        'Intermediário',
        'Força funcional',
        1.75,
        69.0,
        'Segunda, Quarta, Sexta',
        'Parque',
        'Masculino',
        NULL
    );

