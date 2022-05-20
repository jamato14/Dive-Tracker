-- Table Dive

CREATE EXTENSION pgcrypto;

CREATE TABLE dive (
	number 				SERIAL PRIMARY KEY,
	location			TEXT,
	exposure_suit		TEXT,
	air_temp			DECIMAL,
	water_temp			DECIMAL,
	weight				DECIMAL,
	guide				TEXT,
	start_gas_pressure	INT,
	end_gas_pressure	INT,
	gas_type			TEXT
);

CREATE TABLE dive_group (
	dive_numbers 		INT [],
	name				TEXT,
	PRIMARY KEY (dive_numbers, name)
);

CREATE TABLE users (
	id			SERIAL PRIMARY KEY,
	name		TEXT NOT NULL UNIQUE,
	email 		TEXT NOT NULL UNIQUE,
	password	TEXT NOT NULL
);