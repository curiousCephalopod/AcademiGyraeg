CREATE TABLE login
(
	userID CHAR(6) NOT NULL,
	password VARCHAR(20)
	type CHAR(1),
	PRIMARY KEY(userID)
);

CREATE TABLE language
(
	wordID CHAR(6) NOT NULL,
	welsh VARCHAR(20),
	english VARCHAR(20),
	gender char(1)
	PRIMARY KEY(wordID)
);

CREATE TABLE session
(
	sessionID CHAR(6) NOT NULL,
	userId CHAR(6),
	sessionScore TINYINT,
	PRIMARY KEY(sessionID)
);