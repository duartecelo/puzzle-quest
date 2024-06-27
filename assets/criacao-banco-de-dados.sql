CREATE DATABASE puzzle_quest;

USE puzzle_quest;

CREATE TABLE partidas_salvas (
	nome VARCHAR(255),
	id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE partidas_salvas
	ADD COLUMN (
		situacaoTabuleiro CHAR(64),
		vidaAtualJogador1 INT,
        vidaMaximaJogador1 INT,
        ouroAtualJogador1 INT,
        xpAtualJogador1 INT,
        multiplicadorJogador1 INT,
        vidaAtualJogador2 INT,
		vidaMaximaJogador2 INT,
        ouroAtualJogador2 INT,
        xpAtualJogador2 INT,
        multiplicadorJogador2 INT
	);