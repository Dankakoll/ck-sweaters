ALTER TABLE ITEMS
ADD origin varchar(30);
ALTER TABLE ITEMS
ADD origin_id decimal(6,2);

ALTER TABLE SELLERS
ADD origin_id decimal(6,2);