CREATE TABLE IF NOT EXISTS userdata(
	ID SERIAL PRIMARY KEY,
	LOGIN VARCHAR,
	CHAT_ID VARCHAR,
	NUMBER_ROUTE INTEGER,
	IS_ADMIN BOOLEAN
	
	FOREIGN KEY (NUMBER_ROUTE) REFERENCES ROUTE(NUMBER)
);

CREATE TABLE IF NOT EXISTS route(
	ID SERIAL PRIMARY KEY,
	DEPARTURE VARCHAR,
	ARRIVAL VARCHAR,
	NUMBER INTEGER
);