CREATE TABLE IF NOT EXISTS items (
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(30),
    price decimal(6,2),
    updated_at timestamp,
    created_at timestamp
);

