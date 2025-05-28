CREATE DATABASE bancoEstudante;
USE bancoestudante;

ALTER TABLE estudante ADD COLUMN idade int;

SELECT * FROM estudante;
DELETE FROM estudante WHERE id = 2;

CREATE DATABASE IF NOT EXISTS bancoEstudante;
USE bancoEstudante;

CREATE TABLE IF NOT EXISTS estudante (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(200),
    sexo VARCHAR(20),
    turno VARCHAR(20)
);
