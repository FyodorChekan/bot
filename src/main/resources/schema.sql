CREATE TABLE IF NOT EXISTS userdata(
	id SERIAL PRIMARY KEY,
	login VARCHAR,
	chat_id VARCHAR,
	is_admin BOOLEAN
);

CREATE TABLE IF NOT EXISTS route(
	id SERIAL PRIMARY KEY,
	departure VARCHAR,
	arrival VARCHAR,
	number INTEGER,
	create_request DATE
);

CREATE TABLE user_request(
	id SERIAL PRIMARY KEY,
	user_id INTEGER,
	route_id INTEGER,
	request_date date,
	favourite boolean
);