CREATE TABLE users (
id SERIAL PRIMARY KEY,
sid VARCHAR(10) unique,
name VARCHAR(30),
login VARCHAR(10) unique,
password TEXT,
created_at TIMESTAMP DEFAULT now(),
updated_at TIMESTAMP DEFAULT now());

