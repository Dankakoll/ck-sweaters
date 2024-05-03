CREATE TABLE IF NOT EXISTS PRICES (
price_id serial not null primary key,
price text,
origin text,
origin_id bigint,
updated_at date);



