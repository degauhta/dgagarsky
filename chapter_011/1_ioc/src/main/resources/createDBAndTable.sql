DROP DATABASE IF EXISTS dega_spring_usersstorage;

CREATE DATABASE dega_spring_usersstorage;

\connect dega_spring_usersstorage;

CREATE TABLE IF NOT EXISTS USERS
(
  ID       SERIAL PRIMARY KEY,
  NAME    TEXT
);