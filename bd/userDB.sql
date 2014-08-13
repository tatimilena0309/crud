create database UserDB;
use UserDB;
grant all on UserDB.* to 'root'@'localhost' identified by 'admin';

DROP TABLE UserDB.users;
DROP TABLE UserDB.phones;

CREATE TABLE UserDB.users (
  id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(40) NOT NULL,
  email varchar(30) NOT NULL UNIQUE,
  password varchar(20) NOT NULL
);

CREATE TABLE UserDB.phones (
  id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  number varchar(20) NOT NULL,
  user_id INTEGER NOT NULL
);
