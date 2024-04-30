ALTER TABLE items
ALTER COlUMN items_name TYPE text;
ALTER TABLE  items
ALTER COLUMN price TYPE text;
ALTER TABLE items
ALTER COLUMN origin_id TYPE bigint;

ALTER TABLE sellers
ALTER COLUMN seller_name TYPE text;
ALTER TABLE sellers
ALTER COLUMN origin_rate TYPE text;
ALTER TABLE sellers
ALTER COLUMN origin_id TYPE bigint;