
DROP TABLE if exists public.client CASCADE;
DROP TABLE if exists public.account CASCADE;

CREATE TABLE client (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(50),
	birthdate date,
	email VARCHAR(50) UNIQUE
);

CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(50),
	email VARCHAR(50),
	birthday date,
	creationTime timestamp,
	balance bigint,
	id_client INT,
	FOREIGN KEY(id_client) REFERENCES client(id)
);