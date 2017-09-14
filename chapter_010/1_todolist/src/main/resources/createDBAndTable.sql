DROP DATABASE IF EXISTS dega_hibernate_todo;

CREATE DATABASE dega_hibernate_todo;

\connect dega_hibernate_todo;

CREATE TABLE IF NOT EXISTS ITEMS
(
  ID          SERIAL PRIMARY KEY,
  DESCRIPTION TEXT,
  CREATE_DATE TIMESTAMP,
  DONE        BOOLEAN
);

INSERT INTO ITEMS (DESCRIPTION, CREATE_DATE, DONE)
VALUES ('desc 1 f', '2017-04-04 06:00:00', FALSE),
  ('desc 2 t', '2017-04-04 06:00:00', TRUE),
  ('desc 3 f', '2017-04-04 06:00:00', FALSE);