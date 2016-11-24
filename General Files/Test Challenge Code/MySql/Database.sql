DROP DATABASE IF EXISTS Test_Challenge;
CREATE DATABASE Test_Challenge;
USE Test_Challenge;

#CREATING USERS TABLE
CREATE TABLE TAB_Users(
	Id_User INT PRIMARY KEY AUTO_INCREMENT,
	Login_User VARCHAR(16),
	Password_User VARCHAR(16)
);

#ADDING USERS
INSERT INTO TAB_Users(Login_User, Password_User)
VALUES("Chewbacca", "chewBacca1!");
INSERT INTO TAB_Users(Login_User, Password_User)
VALUES("C3PO", "c3PO2!");
INSERT INTO TAB_Users(Login_User, Password_User)
VALUES("R2D2", "R2d23!");
INSERT INTO TAB_Users(Login_User, Password_User)
VALUES("BB-8", "BB-8bb4!");
INSERT INTO TAB_Users(Login_User, Password_User)
VALUES("Kenobi", "Kenobi5!");
INSERT INTO TAB_Users(Login_User, Password_User)
VALUES("Yoda", "YODA6!");
