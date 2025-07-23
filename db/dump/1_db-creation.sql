
DROP TABLE if exists public.client CASCADE;
DROP TABLE if exists public.account CASCADE;
DROP TABLE IF EXISTS public.client_insurance CASCADE;
DROP TABLE IF EXISTS public.bank_advisor CASCADE;
DROP TABLE IF EXISTS public.insurance CASCADE;
DROP TABLE IF EXISTS public.specialty CASCADE;
DROP TABLE IF EXISTS public.person CASCADE;

CREATE TABLE person (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(50),
    birthdate DATE,
    email VARCHAR(50) UNIQUE
);

CREATE TABLE client (
	id UUID PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES person(id)
);

CREATE TABLE bank_advisor (
    id UUID PRIMARY KEY,
    hiring_date DATE,
    specialty VARCHAR(50),
    FOREIGN KEY (id) REFERENCES person(id)
);

CREATE TABLE client_bank_advisor (
    id_client UUID,
    id_advisor UUID,
    PRIMARY KEY (id_client, id_advisor),
    FOREIGN KEY (id_client) REFERENCES client(id),
    FOREIGN KEY (id_advisor) REFERENCES bank_advisor(id)
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


