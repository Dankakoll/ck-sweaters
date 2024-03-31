CREATE TABLE IF NOT EXISTS items (
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(30),
    price decimal(6,2),
    updated_at timestamp,
    created_at timestamp
);
CREATE TABLE IF NOT EXISTS Sellers(
    id serial not null primary key,
    seller_name varchar(30),
    origin varchar(30),
    origin_rate decimal (6,2)
);
CREATE TABLE IF NOT EXISTS Users(
    id serial not null primary key,
    login varchar(30),
    password varchar (40),
    email varchar (40)
);

