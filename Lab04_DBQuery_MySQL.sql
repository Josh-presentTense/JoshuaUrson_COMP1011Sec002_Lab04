CREATE DATABASE COMP1011Lab04;

USE COMP1011Lab04;

-- Query for Lab4 of COMP1011
-- By: Joshua Urson 200464522

-- Used to delete tables if needed
DROP TABLE IF EXISTS players_and_games;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS players;

CREATE TABLE games (
	game_id 	int NOT NULL AUTO_INCREMENT,
	game_title 	nvarchar(50) NOT NULL,
	PRIMARY KEY (game_id)
);
ALTER TABLE games AUTO_INCREMENT=100;

CREATE TABLE players (
	player_id 	int NOT NULL AUTO_INCREMENT,
	first_name 	nvarchar(50) NOT NULL,
    last_name 	nvarchar(50) NOT NULL,
    address 	nvarchar(50),
    postal_code varchar(6),
    province 	varchar(3),
    phone_number bigint,
	PRIMARY KEY (player_id)
);
ALTER TABLE players AUTO_INCREMENT=100;

CREATE TABLE players_and_games (
	player_game_id 	int NOT NULL AUTO_INCREMENT,
	game_id 		int,
    player_id 		int,
    playing_date 	nvarchar(10),
    score 			varchar(100),
	PRIMARY KEY (player_game_id),
    FOREIGN KEY (game_id) REFERENCES games(game_id),
    FOREIGN KEY (player_id) REFERENCES players(player_id)
);

-- View content of tables
SELECT * FROM games;
SELECT * FROM players;
SELECT * FROM players_and_games;

-- INSERT into games
INSERT INTO games (game_title) VALUES ('Super Mario 64');
INSERT INTO games (game_title) VALUES ('Risk of Rain 2');
INSERT INTO games (game_title) VALUES ('Minecraft');
INSERT INTO games (game_title) VALUES ('Mario Party Superstars');

-- INSERT into players
INSERT INTO players (first_name, last_name, address, postal_code, province, phone_number) VALUES ('John', 'Smith', '123 Qwerty St', 'L1L2L2', 'ON', 1112223344);
INSERT INTO players (first_name, last_name, address, postal_code, province, phone_number) VALUES ('Jane', 'Doe', '456 Asdf St', 'L2L3L3', 'ON', 2223334455);

-- INSERT into players_and_games
INSERT INTO players_and_games (game_id, player_id, playing_date, score) VALUES (100, 100, '2021-04-11', '54 Stars');
INSERT INTO players_and_games (game_id, player_id, playing_date, score) VALUES (101, 100, '2021-05-03', 'Stage 28');
INSERT INTO players_and_games (game_id, player_id, playing_date, score) VALUES (102, 100, '2021-05-03', 'Level: 17, Night: 6');
INSERT INTO players_and_games (game_id, player_id, playing_date, score) VALUES (103, 101, '2021-05-03', 'Stars: 4, Coins: 75');
INSERT INTO players_and_games (game_id, player_id, playing_date, score) VALUES (100, 101, '2021-05-03', '61 Stars');

-- GRAB based on game_id
SELECT	PG.player_game_id, G.game_id, G.game_title, PG.score, PG.playing_date, P.player_id, P.first_name, P.last_name
FROM	players_and_games AS PG
JOIN	players AS P
	ON	PG.player_id = P.player_id
JOIN	games as G
	ON	PG.game_id = G.game_id
WHERE G.game_id = 100;
    
-- GRAB based on player_id
SELECT	PG.player_game_id, P.player_id, P.first_name, P.last_name, G.game_id, G.game_title, PG.score, PG.playing_date
FROM	players_and_games AS PG
JOIN	players AS P
	ON	PG.player_id = P.player_id
JOIN	games as G
	ON	PG.game_id = G.game_id
WHERE P.player_id = 100;

