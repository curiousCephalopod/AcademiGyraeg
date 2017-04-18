CREATE TABLE login
(
    userID INT(5) NOT NULL AUTO_INCREMENT,
    username VARCHAR(56) NOT NULL,
    password VARCHAR(256) NOT NULL,
    userType INT(1) NOT NULL,
    PRIMARY KEY(userID)
);

CREATE TABLE words
(
    wordID INT(5) NOT NULL AUTO_INCREMENT,
    english VARCHAR(56) NOT NULL,
    welsh VARCHAR(56) NOT NULL,
    wordType VARCHAR(15) NOT NULL,
    wordGender CHAR(1) NOT NULL,
    PRIMARY KEY(wordID)
);

CREATE TABLE results
(
    testID INT(5) NOT NULL AUTO_INCREMENT,
    userID INT(5) NOT NULL,
    quizType char(1) NOT NULL,
    result INT(2) NOT NULL,
    outOf INT(2) NOT NULL,
    dateTaken DateTime NOT NULL,
    PRIMARY KEY(testID),
    FOREIGN KEY(userID) REFERENCES login(userID)
);
