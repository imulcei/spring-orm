
DROP TABLE if exists public.client CASCADE;
DROP TABLE if exists public.account CASCADE;

CREATE TABLE client (
	id UUID PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(50),
	birthdate date,
	email VARCHAR(50) UNIQUE
);

CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	creation_time timestamp,
	balance bigint,
	id_client UUID,
	FOREIGN KEY(id_client) REFERENCES client(id)
);

CREATE TABLE insurance(
   id SERIAL PRIMARY KEY,
   name VARCHAR(50)
);

CREATE TABLE client_insurance(
   id_client UUID,
   id_insurance INT,
   PRIMARY KEY(id_client, id_insurance),
   FOREIGN KEY(id_client) REFERENCES client(id),
   FOREIGN KEY(id_insurance) REFERENCES insurance(id)
);
