CREATE TABLE IF NOT EXISTS 'Items'(
 'id' serial not null primary key,
 'origin_name' varchar(30),
 'price' decimal (6,2),
 'updated_at' timestamp
);