CREATE TABLE login
(
    userID INT(5) NOT NULL,
    username CHAR(56) NOT NULL,
    password VARCHAR(256) NOT NULL,
    userType int(1) NOT NULL,
    PRIMARY KEY(userID)
)

CREATE TABLE words
(
    wordID INT(5) NOT NULL,
    english VARCHAR(56) NOT NULL,
    welsh VARCHAR(56) NOT NULL,
    wordType char(1) NOT NULL,
    wordGender char(1) NOT NULL,
    PRIMARY KEY(wordID)
)

CREATE TABLE results
(
    testID int(5) NOT NULL,
    username CHAR(56) NOT NULL,
    restult int(2) NOT NULL,
    outOf int(2) NOT NULL,
    PRIMARY KEY(testID),
    FOREIGN KEY(username) REFERENCES login(username)
)